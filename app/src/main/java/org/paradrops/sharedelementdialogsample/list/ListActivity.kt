package org.paradrops.sharedelementdialogsample.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ListView
import org.paradrops.sharedelementdialog.SharedElementDialog
import org.paradrops.sharedelementdialogsample.R
import org.paradrops.sharedelementdialogsample.getResourceIdUri

class ListActivity : AppCompatActivity() {
    companion object {
        fun navigateIntent(context: Context) : Intent = Intent(context, ListActivity::class.java)
    }

    private val listView by lazy { findViewById<ListView>(R.id.listView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        listView.adapter = ListViewItemAdapter.create(this)
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->

            val itemView = view as ListItemView

            val item = ListViewItemAdapter.items()[position]
            SharedElementDialog.Builder()
                    .setPositiveButton("OK", null)
                    .setTitle(item.title)
                    .setMessage(item.message)
                    .setImageUri(resources.getResourceIdUri(item.imageResId))
                    .setSharedRootViewContainer(itemView.image)
                    .setSharedContentView(itemView.image)
                    .create()
                    .show(this)
        }
    }
}
