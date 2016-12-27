package org.paradrops.sharedelementdialogsample

import android.content.Context
import org.paradrops.sharedelementdialogsample.click.ClickEventActivity
import org.paradrops.sharedelementdialogsample.customlayout.CustomLayoutActivity
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

        fun goClickEventView(context: Context) {
            context.startActivity(ClickEventActivity.navigateIntent(context))
        }

        fun goCustomLayoutView(context: Context) {
            context.startActivity(CustomLayoutActivity.navigateIntent(context))
        }
    }
}
