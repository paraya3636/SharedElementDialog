package org.paradrops.sharedelementdialogsample.list

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import org.paradrops.sharedelementdialogsample.R

class ListViewItemAdapter(context: Context, resource: Int, items: Array<out Item>) : ArrayAdapter<ListViewItemAdapter.Item>(context, resource, items) {
    companion object {
        fun create(context: Context) : ListViewItemAdapter {
            return ListViewItemAdapter(context, R.layout.view_list_item, items())
        }

        fun items() : Array<Item> {
            return (0..10)
                    .map {
                        when(it) {
                            0 -> R.drawable.cat01
                            1 -> R.drawable.cat02
                            2 -> R.drawable.cat03
                            3 -> R.drawable.cat04
                            4 -> R.drawable.cat05
                            5 -> R.drawable.cat06
                            6 -> R.drawable.cat07
                            7 -> R.drawable.cat08
                            8 -> R.drawable.cat09
                            9 -> R.drawable.cat10
                            10 -> R.drawable.cat11
                            else -> R.drawable.cat01
                        }
                    }
                    .map { Item(it, "Pretty cat!!", "Cats are connoisseurs of comfort. The smallest feline is a masterpiece. Cats are living adornments.") }
                    .toTypedArray()
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view !is ListItemView) {
            view = ListItemView.inflate(context)
        }

        if (view is ListItemView) {
            val item = getItem(position)
            view.image.setImageResource(item.imageResId)
            view.title.text = item.title
            view.message.text = item.message
        }

        return view
    }

    class Item(val imageResId: Int, val title: String, val message: String)
}
