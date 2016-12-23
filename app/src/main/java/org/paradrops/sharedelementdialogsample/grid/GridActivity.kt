package org.paradrops.sharedelementdialogsample.grid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.GridView
import org.paradrops.sharedelementdialog.SharedElementDialog
import org.paradrops.sharedelementdialogsample.R
import org.paradrops.sharedelementdialogsample.getResourceIdUri

class GridActivity : AppCompatActivity() {
    companion object {
        fun navigateIntent(context: Context) : Intent = Intent(context, GridActivity::class.java)
    }

    private val gridView by lazy { findViewById(R.id.gridView) as GridView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)
        gridView.adapter = CatsImageViewAdapter.create(this)
        gridView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val resId = CatsImageViewAdapter.items()[position]
            SharedElementDialog.Builder()
                    .setTitle("Cat")
                    .setPositiveButton("OK")
                    .setImageUri(resources.getResourceIdUri(resId))
                    .setSharedRootViewContainer(view)
                    .setSharedContentView(view)
                    .create()
                    .show(this)
        }
    }
}
