package tang.chinwe.lib_page.expand

import android.view.View

fun View.gone() {
    this.visibility = View.GONE
}

fun View.isGone(): Boolean {
    return this.visibility == View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun View.isInVisible(): Boolean {
    return this.visibility == View.INVISIBLE
}

fun View.visible(visible: Boolean) {
    if (visible) {
        visible()
    } else {
        gone()
    }
}