package tang.chinwe.lib_page.utils

import android.view.View
import android.view.ViewStub

fun stubView(rootView: View, id: Int?, layout: Int?): View? {
    if (id == null || layout == null || layout == 0)
        return null
    val stub = rootView.findViewById<ViewStub>(id)
    if (stub != null) {
        stub.layoutResource = layout
        return stub.inflate()
    }
    return null
}