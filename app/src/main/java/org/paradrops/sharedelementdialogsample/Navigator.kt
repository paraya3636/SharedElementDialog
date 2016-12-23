package org.paradrops.sharedelementdialogsample

import android.content.Context
import org.paradrops.sharedelementdialogsample.grid.GridActivity

class Navigator {
    companion object {
        fun goGridView(context: Context) {
            context.startActivity(GridActivity.navigateIntent(context))
        }
    }
}
