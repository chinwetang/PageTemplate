package tang.chinwe.pagetemplate

import android.app.Application
import tang.chinwe.lib_page.page.PageManager

class MyApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        PageManager.defaultIsPage=true
    }
}