package tang.chinwe.pagetemplate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import tang.chinwe.lib_page.base.PageFragmentActivity
import tang.chinwe.lib_page.page.page_default.DefaultPageCallBack
import tang.chinwe.lib_page.page.page_default.DefaultPageImpl

class TestActivity : PageFragmentActivity<DefaultPageImpl, DefaultPageCallBack>() {
    override fun newPageCallBack() = object : DefaultPageCallBack() {
        override var layout: Int? = R.layout.activity_test
    }
}
