package org.paradrops.sharedelementdialogsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private val listView by lazy { findViewById<ListView>(R.id.listView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView.adapter = MainListViewItemAdapter.create(this)
        listView.setOnItemClickListener { adapterView, view, position, id ->
            when(position) {
                0 -> Navigator.goGridView(this)
                1 -> Navigator.goListView(this)
                2 -> Navigator.goClickEventView(this)
                3 -> Navigator.goCustomLayoutView(this)
            }
        }
    }
}
