package org.paradrops.sharedelementdialogsample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.paradrops.sharedelementdialog.SharedElementDialog

class MainActivity : AppCompatActivity() {

    private val gridViewButton by lazy { findViewById(R.id.gridViewButton) as Button }
    private val listViewButton by lazy { findViewById(R.id.listViewButton) as Button }

    private val imageRootView by lazy { findViewById(R.id.imageRootView) as CardView }
    private val image by lazy { findViewById(R.id.image) as ImageView }
    private val imageText by lazy { findViewById(R.id.imageText) as TextView }

    private val defaultDialogButton by lazy { findViewById(R.id.defaultDialogButton) as Button }
    private val dialogButton by lazy { findViewById(R.id.dialogButton) as Button }
    private val noTitleDialogButton by lazy { findViewById(R.id.noTitleDialogButton) as Button }
    private val imageOnlyDialogButton by lazy { findViewById(R.id.imageOnlyDialogButton) as Button }

    private val sharedElementDialog by lazy {
        SharedElementDialog.Builder()
                .setTitle("Title")
                .setMessage("Message")
                .setPositiveButton("OK")
                .setNegativeButton("NO")
                .setNeutralButton("CANCEL")
                .setImageUri(resources.getResourceIdUri(R.drawable.cat01))
                .setSharedRootViewContainer(imageRootView)
                .setSharedContentView(image)
                .setTag("SharedElementDialog")
                .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridViewButton.setOnClickListener {
            Navigator.goGridView(this)
        }

        listViewButton.setOnClickListener {
            Navigator.goListView(this)
        }

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
            sharedElementDialog.show(this)
        }

        noTitleDialogButton.setOnClickListener {
            SharedElementDialog.Builder()
                    .setMessage(imageText.text.toString())
                    .setPositiveButton("OK")
                    .setSharedContentView(imageText)
                    .create()
                    .show(this)
        }

        imageOnlyDialogButton.setOnClickListener {
            SharedElementDialog.Builder()
                    .setPositiveButton("OK")
                    .setNeutralButton("CANCEL")
                    .setImageUri(resources.getResourceIdUri(R.drawable.cat01))
                    .setSharedRootViewContainer(imageRootView)
                    .setSharedContentView(image)
                    .create()
                    .show(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        sharedElementDialog.onActivityResult(requestCode, resultCode, data, object : SharedElementDialog.SharedElementDialogCallback {
            override fun onClickPositiveButton() {
                Toast.makeText(this@MainActivity, "On click positive button!", Toast.LENGTH_SHORT).show()
            }

            override fun onClickNegativeButton() {
                Toast.makeText(this@MainActivity, "On click Negative button!", Toast.LENGTH_SHORT).show()
            }

            override fun onClickNeutralButton() {
                Toast.makeText(this@MainActivity, "On click Neutral button!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
