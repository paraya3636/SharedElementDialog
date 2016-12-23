package org.paradrops.sharedelementdialogsample

import android.content.Context
import org.paradrops.sharedelementdialogsample.grid.GridActivity
import org.paradrops.sharedelementdialogsample.list.ListActivity

class Navigator {
    companion object {
        fun goGridView(context: Context) {
            context.startActivity(GridActivity.navigateIntent(context))
        }

        fun goListView(context: Context) {
            context.startActivity(ListActivity.navigateIntent(context))
        }
    }
}
