package org.paradrops.sharedelementdialogsample.grid

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import org.paradrops.sharedelementdialogsample.R

class CatsImageViewAdapter(context: Context, resource: Int, items: Array<out Int>) : ArrayAdapter<Int>(context, resource, items) {
    companion object {
        fun create(context: Context) : CatsImageViewAdapter {
            return CatsImageViewAdapter(context, R.layout.view_grid_image_item, items())
        }

        fun items() : Array<Int> {
            return arrayOf(
                    R.drawable.cat01,
                    R.drawable.cat02,
                    R.drawable.cat03,
                    R.drawable.cat04,
                    R.drawable.cat05,
                    R.drawable.cat06,
                    R.drawable.cat07,
                    R.drawable.cat08,
                    R.drawable.cat09,
                    R.drawable.cat10,
                    R.drawable.cat11
            )
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = GridImageItemView.inflate(context)
        }

        (view as GridImageItemView).setImageResource(getItem(position))
        return view
    }
}
