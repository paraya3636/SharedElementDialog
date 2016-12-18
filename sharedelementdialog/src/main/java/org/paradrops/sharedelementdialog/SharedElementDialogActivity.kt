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
import android.widget.RelativeLayout
import android.widget.Space
import android.widget.TextView

class SharedElementDialogActivity : AppCompatActivity() {
    companion object {
        private val ImageResourceId = "ImageResourceId"
        private val AccentColor = "AccentColor"
        private val DialogInfo = "DialogInfo"

        fun show(context: Context, sharedViewContainer: View, sharedImage: View, imageResId: Int) {
            val intent = getNavigateIntent(context)
            intent.putExtra(ImageResourceId, imageResId)

            val container = Pair<View, String>(sharedViewContainer, context.getString(R.string.CommonSharedViewContainer))
            val image = Pair<View, String>(sharedImage, context.getString(R.string.CommonSharedImage))

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, container, image)
            context.startActivity(intent, options.toBundle())
        }

        fun show(context: Context, dialogInfo: DialogInfo) {
            val intent = getNavigateIntent(context)
            intent.putExtra(DialogInfo, dialogInfo)
            context.startActivity(intent)
        }

        private fun getNavigateIntent(context: Context) : Intent {
            val intent = Intent(context, SharedElementDialogActivity::class.java)
            val value = TypedValue()
            context.theme.resolveAttribute(R.attr.colorAccent, value, true)
            intent.putExtra(AccentColor, value.data)
            return intent
        }
    }

    private val dialogInfo by lazy { intent.getParcelableExtra<DialogInfo>(DialogInfo) }

    private val rootContainer by lazy { findViewById(R.id.rootContainer) as RelativeLayout}
    private val title by lazy { findViewById(R.id.title) as TextView }
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

        dialogInfo.title?.let {
            title.text = it
            title.visibility = VISIBLE
        }

        dialogInfo.message?.let {
            message.text = it
            message.visibility = VISIBLE
        }

        val buttonTextColor = intent.getIntExtra(AccentColor, 0)
        dialogInfo.neutralButtonText?.let {
            neutralButton.text = it
            neutralButton.setTextColor(buttonTextColor)
            neutralButton.visibility = VISIBLE
        }

        dialogInfo.negativeButtonText?.let {
            negativeButton.text = it
            negativeButton.setTextColor(buttonTextColor)
            neutralButton.visibility = VISIBLE
        }

        dialogInfo.positiveButtonText?.let {
            positiveButton.text = it
            positiveButton.setTextColor(buttonTextColor)
            positiveButton.visibility = VISIBLE
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
