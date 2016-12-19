package org.paradrops.sharedelementdialogsample

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.paradrops.sharedelementdialog.SharedElementDialog

class MainActivity : AppCompatActivity() {

    private val imageRootView by lazy { findViewById(R.id.imageRootView) as CardView }
    private val image by lazy { findViewById(R.id.image) as ImageView }
    private val imageText by lazy { findViewById(R.id.imageText) as TextView }

    private val defaultDialogButton by lazy { findViewById(R.id.defaultDialogButton) as Button }
    private val dialogButton by lazy { findViewById(R.id.dialogButton) as Button }
    private val noTitleDialogButton by lazy { findViewById(R.id.noTitleDialogButton) as Button }
    private val imageOnlyDialogButton by lazy { findViewById(R.id.imageOnlyDialogButton) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        defaultDialogButton.setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle("Title")
                    .setMessage("Message")
                    .setPositiveButton("OK", null)
                    .setNegativeButton("NO", null)
                    .setNeutralButton("CANCEL", null)
                    .create()
                    .show()
        }

        dialogButton.setOnClickListener {
            SharedElementDialog.Builder()
                    .setTitle("Title")
                    .setMessage("Message")
                    .setPositiveButton("OK")
                    .setNegativeButton("NO")
                    .setNeutralButton("CANCEL")
                    .setImageUri(resources.getResourceIdUri(R.drawable.cat01))
                    .setSharedRootView(imageRootView)
                    .setSharedChildView(image)
                    .create()
                    .show(this)
        }

        noTitleDialogButton.setOnClickListener {
            SharedElementDialog.Builder()
                    .setMessage(imageText.text.toString())
                    .setPositiveButton("OK")
                    .setSharedChildView(imageText)
                    .create()
                    .show(this)
        }

        imageOnlyDialogButton.setOnClickListener {
            SharedElementDialog.Builder()
                    .setPositiveButton("OK")
                    .setNeutralButton("CANCEL")
                    .setImageUri(resources.getResourceIdUri(R.drawable.cat01))
                    .setSharedRootView(imageRootView)
                    .setSharedChildView(image)
                    .create()
                    .show(this)
        }
    }
}
