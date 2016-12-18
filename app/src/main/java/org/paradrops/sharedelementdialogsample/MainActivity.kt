package org.paradrops.sharedelementdialogsample

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import org.paradrops.sharedelementdialog.DialogInfo
import org.paradrops.sharedelementdialog.SharedElementDialogActivity

class MainActivity : AppCompatActivity() {

    private val image by lazy { findViewById(R.id.image) as ImageView }
    private val button by lazy { findViewById(R.id.button) as Button }
    private val alertButton by lazy { findViewById(R.id.alertButton) as Button }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val dialogInfo = DialogInfo("Title", "Message", "CANCEL", "NO", "OK")
            SharedElementDialogActivity.show(this, dialogInfo)
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
