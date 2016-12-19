package org.paradrops.sharedelementdialogsample

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import org.paradrops.sharedelementdialog.SharedElementDialog

class MainActivity : AppCompatActivity() {

    private val image by lazy { findViewById(R.id.image) as ImageView }
    private val button by lazy { findViewById(R.id.button) as Button }
    private val alertButton by lazy { findViewById(R.id.alertButton) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            SharedElementDialog.Builder()
                    .setTitle("Title")
                    .setMessage("Message")
                    .setPositiveButton("OK")
                    .setNegativeButton("NO")
                    .setNeutralButton("CANCEL")
                    .setImageUri(resources.getResourceIdUri(R.drawable.cat01))
                    .setSharedRootView(image)
                    .setSharedChildView(image)
                    .create()
                    .show(this)
        }

        alertButton.setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle("Title")
                    .setMessage("Message")
                    .setPositiveButton("OK", null)
                    .setNegativeButton("NO", null)
                    .setNeutralButton("CANCEL", null)
                    .create()
                    .show()
        }
    }
}