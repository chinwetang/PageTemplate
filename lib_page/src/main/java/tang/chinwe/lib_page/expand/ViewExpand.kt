package tang.chinwe.lib_page.expand

import android.view.View

internal fun View.gone() {
    this.visibility = View.GONE
}

internal fun View.isGone(): Boolean {
    return this.visibility == View.GONE
}

internal fun View.visible() {
    this.visibility = View.VISIBLE
}

internal fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

internal fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

internal fun View.isInVisible(): Boolean {
    return this.visibility == View.INVISIBLE
}

internal fun View.visible(visible: Boolean) {
    if (visible) {
        visible()
    } else {
        gone()
    }
}