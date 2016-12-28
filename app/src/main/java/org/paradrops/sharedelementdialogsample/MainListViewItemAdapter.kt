package org.paradrops.sharedelementdialogsample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MainListViewItemAdapter(context: Context, resource: Int, items: Array<out String>) : ArrayAdapter<String>(context, resource, items) {
    companion object {
        fun create(context: Context) : MainListViewItemAdapter {
            return MainListViewItemAdapter(context, R.layout.view_simple_list_item, items())
        }

        fun items() : Array<String> {
            return arrayOf("GridView", "ListView", "OnClickListener & Cancelable", "Custom layout")
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.view_simple_list_item, parent, false)
        }

        val inflatedView = view as View
        val index = inflatedView.findViewById(R.id.index) as TextView
        val title = inflatedView.findViewById(R.id.title) as TextView
        index.text = (position + 1).toString()
        title.text = items()[position]
        return inflatedView
    }
}
