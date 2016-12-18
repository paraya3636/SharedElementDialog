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

        fun show(context: Context, sharedViewContainer: View, sharedImage: View, imageResId: Int) {
            val intent = getNavigateIntent(context)
            intent.putExtra(ImageResourceId, imageResId)

            val container = Pair<View, String>(sharedViewContainer, context.getString(R.string.CommonSharedViewContainer))
            val image = Pair<View, String>(sharedImage, context.getString(R.string.CommonSharedImage))

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, container, image)
            context.startActivity(intent, options.toBundle())
        }

        fun show(context: Context) {
            context.startActivity(getNavigateIntent(context))
        }

        private fun getNavigateIntent(context: Context) : Intent {
            val intent = Intent(context, SharedElementDialogActivity::class.java)
            intent.putExtra(AccentColor, getAccentColor(context))
            return intent
        }

        private fun getAccentColor(context: Context) : Int {
            val value = TypedValue()
            context.theme.resolveAttribute(R.attr.colorAccent, value, true)
            return value.data
        }
    }

    private val rootContainer by lazy { findViewById(R.id.rootContainer) as RelativeLayout}
    private val textSpacerNoTitle by lazy { findViewById(R.id.textSpacerNoTitle) as Space }
    private val title by lazy { findViewById(R.id.title) as TextView }
    private val message by lazy { findViewById(R.id.message) as TextView }

    private val neutralButton by lazy { findViewById(R.id.neutralButton) as Button }
    private val negativeButton by lazy { findViewById(R.id.negativeButton) as Button }
    private val positiveButton by lazy { findViewById(R.id.positiveButton) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element_dialog)

        /*
        intent.getIntExtra(ImageResourceId, 0).let {
            image.setImageResource(it)
        }
        */

        rootContainer.setOnClickListener {
            finishAfterTransition()
        }

        message.text = "Message"

        neutralButton.text = "CANCEL"
        negativeButton.text = "NO"
        positiveButton.text = "OK"

        val buttonTextColor = intent.getIntExtra(AccentColor, 0)
        neutralButton.setTextColor(buttonTextColor)
        negativeButton.setTextColor(buttonTextColor)
        positiveButton.setTextColor(buttonTextColor)

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
