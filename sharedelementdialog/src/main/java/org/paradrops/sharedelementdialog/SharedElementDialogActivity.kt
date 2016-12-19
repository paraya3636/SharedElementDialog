package org.paradrops.sharedelementdialog

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.View
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.*

class SharedElementDialogActivity : AppCompatActivity() {
    companion object {
        private val AccentColor = "AccentColor"
        private val SharedElementDialog = "SharedElementDialog"

        fun show(context: Context, sharedElementDialog: SharedElementDialog, sharedRootView: View?, sharedChildView: View?) {
            val intent = getNavigateIntent(context)
            intent.putExtra(SharedElementDialog, sharedElementDialog)

            val shares: MutableList<Pair<View, String>> = ArrayList()
            sharedRootView?.let {
                val pair = Pair<View, String>(it, context.getString(R.string.shared_root_view))
                shares.add(pair)
            }

            sharedChildView?.let {
                val pair = Pair<View, String>(it, context.getString(R.string.shared_content_view))
                shares.add(pair)
            }

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *shares.toTypedArray())
            context.startActivity(intent, options.toBundle())
        }

        private fun getNavigateIntent(context: Context) : Intent {
            val intent = Intent(context, SharedElementDialogActivity::class.java)
            val value = TypedValue()
            context.theme.resolveAttribute(R.attr.colorAccent, value, true)
            intent.putExtra(AccentColor, value.data)
            return intent
        }
    }

    private val dialogInfo by lazy { intent.getParcelableExtra<SharedElementDialog>(SharedElementDialog) }

    private val rootContainer by lazy { findViewById(R.id.rootContainer) as RelativeLayout}
    private val title by lazy { findViewById(R.id.title) as TextView }
    private val image by lazy { findViewById(R.id.image) as ImageView }
    private val message by lazy { findViewById(R.id.message) as TextView }
    private val neutralButton by lazy { findViewById(R.id.neutralButton) as Button }
    private val negativeButton by lazy { findViewById(R.id.negativeButton) as Button }
    private val positiveButton by lazy { findViewById(R.id.positiveButton) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element_dialog)

        rootContainer.setOnClickListener {
            finishAfterTransition()
        }

        if (!dialogInfo.title.isNullOrEmpty()) {
            title.text = dialogInfo.title
            title.visibility = VISIBLE
        }

        dialogInfo.imageUri?.let {
            image.setImageURI(it)
            image.scaleType = dialogInfo.imageScaleType
            image.visibility = VISIBLE
        }

        if (!dialogInfo.message.isNullOrEmpty()) {
            message.text = dialogInfo.message
            message.visibility = VISIBLE
        }

        val buttonTextColor = intent.getIntExtra(AccentColor, 0)
        if (!dialogInfo.positiveButtonText.isNullOrEmpty()) {
            positiveButton.text = dialogInfo.positiveButtonText
            positiveButton.setTextColor(buttonTextColor)
            positiveButton.visibility = VISIBLE
        }

        if (!dialogInfo.negativeButtonText.isNullOrEmpty()) {
            negativeButton.text = dialogInfo.negativeButtonText
            negativeButton.setTextColor(buttonTextColor)
            negativeButton.visibility = VISIBLE
        }

        if (!dialogInfo.neutralButtonText.isNullOrEmpty()) {
            neutralButton.text = dialogInfo.neutralButtonText
            neutralButton.setTextColor(buttonTextColor)
            neutralButton.visibility = VISIBLE
        }

        neutralButton.setOnClickListener {
            finishAfterTransition()
        }

        negativeButton.setOnClickListener {
            finishAfterTransition()
        }

        positiveButton.setOnClickListener {
            finishAfterTransition()
        }
    }
}