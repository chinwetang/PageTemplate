package tang.chinwe.lib_page.page

import android.os.Handler
import android.os.Looper

interface IRootLayout {
    companion object {
        val publicHandler = Handler(Looper.getMainLooper())
    }
    fun rootLayout(): Int
    fun onCreatePage(){}
}