package org.paradrops.sharedelementdialog

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import org.paradrops.sharedelementdialog.SharedElementDialog.Companion.ActivityResultKeyDialogTag
import java.util.*

class SharedElementDialogActivity : AppCompatActivity() {
    companion object {
        private val AccentColor = "AccentColor"
        private val SharedElementDialogInfo = "SharedElementDialogInfo"

        fun show(context: Context, sharedElementDialog: SharedElementDialog, sharedRootView: View?, sharedChildView: View?) {
            val intent = getNavigateIntent(context)
            intent.putExtra(SharedElementDialogInfo, sharedElementDialog)

            val shares: MutableList<Pair<View, String>> = ArrayList()
            sharedRootView?.let {
                val pair = Pair<View, String>(it, context.getString(R.string.shared_root_view_container))
                shares.add(pair)
            }

            sharedChildView?.let {
                val pair = Pair<View, String>(it, context.getString(R.string.shared_content_view))
                shares.add(pair)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *shares.toTypedArray())
                context.startActivityForResult(intent, SharedElementDialog.ActivityRequestCode, options.toBundle())
            } else if (context is Activity) {
                context.startActivityForResult(intent, SharedElementDialog.ActivityRequestCode)
            } else {
                context.startActivity(intent)
            }
        }

        private fun getNavigateIntent(context: Context) : Intent {
            val intent = Intent(context, SharedElementDialogActivity::class.java)
            val value = TypedValue()
            context.theme.resolveAttribute(R.attr.colorAccent, value, true)
            intent.putExtra(AccentColor, value.data)
            return intent
        }
    }

    private val dialogInfo by lazy { intent.getParcelableExtra<SharedElementDialog>(SharedElementDialogInfo) }

    private val title by lazy { findViewById<TextView>(R.id.title) }
    private val image by lazy { findViewById<ImageView>(R.id.image) }
    private val message by lazy { findViewById<TextView>(R.id.message) }
    private val neutralButton by lazy { findViewById<Button>(R.id.neutralButton) }
    private val negativeButton by lazy { findViewById<Button>(R.id.negativeButton) }
    private val positiveButton by lazy { findViewById<Button>(R.id.positiveButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()

        if (dialogInfo.cancelable) {
            findViewById<View>(R.id.rootContainer).setOnClickListener {
                finishDialog(DialogActivityResultCode.ON_CANCEL.value)
            }
        }

        if (!dialogInfo.title.isNullOrEmpty()) {
            title?.text = dialogInfo.title
            title?.visibility = VISIBLE
        }

        dialogInfo.imageUri.let {
            image?.setImageURI(it)
            image?.scaleType = dialogInfo.imageScaleType
            image?.visibility = VISIBLE
        }

        if (!dialogInfo.message.isNullOrEmpty()) {
            message?.text = dialogInfo.message
            message?.visibility = VISIBLE
        }

        val buttonTextColor = intent.getIntExtra(AccentColor, 0)
        if (!dialogInfo.positiveButtonText.isNullOrEmpty()) {
            positiveButton?.text = dialogInfo.positiveButtonText
            positiveButton?.setTextColor(buttonTextColor)
            positiveButton?.visibility = VISIBLE
        }

        if (!dialogInfo.negativeButtonText.isNullOrEmpty()) {
            negativeButton?.text = dialogInfo.negativeButtonText
            negativeButton?.setTextColor(buttonTextColor)
            negativeButton?.visibility = VISIBLE
        }

        if (!dialogInfo.neutralButtonText.isNullOrEmpty()) {
            neutralButton?.text = dialogInfo.neutralButtonText
            neutralButton?.setTextColor(buttonTextColor)
            neutralButton?.visibility = VISIBLE
        }

        positiveButton?.setOnClickListener {
            finishDialog(DialogActivityResultCode.ON_CLICK_POSITIVE_BUTTON.value)
        }

        negativeButton?.setOnClickListener {
            finishDialog(DialogActivityResultCode.ON_CLICK_NEGATIVE_BUTTON.value)
        }

        neutralButton?.setOnClickListener {
            finishDialog(DialogActivityResultCode.ON_CLICK_NEUTRAL_BUTTON.value)
        }
    }

    private fun initContentView() {
        when(dialogInfo.customLayoutType) {
            CustomLayoutType.None -> {
                setContentView(R.layout.activity_dialog)
            }
            CustomLayoutType.ContentOnly -> {
                setContentView(R.layout.activity_content_custom_dialog)
                LayoutInflater.from(this).inflate(dialogInfo.customViewLayoutResId, null).let {
                    (findViewById<FrameLayout>(R.id.contentArea)).addView(it)
                }
            }
            CustomLayoutType.Full -> {
                setContentView(R.layout.activity_full_custom_dialog)
                LayoutInflater.from(this).inflate(dialogInfo.customViewLayoutResId, null).let {
                    (findViewById<FrameLayout>(R.id.contentContainer)).addView(it)
                }
            }
        }
    }

    private fun finishDialog(resultCode: Int) {
        val intent = Intent().run {
            val bundle = Bundle()
            bundle.putString(ActivityResultKeyDialogTag, dialogInfo.tag)
            putExtras(bundle)
        }

        setResult(resultCode, intent)
        supportFinishAfterTransition()
    }
}
