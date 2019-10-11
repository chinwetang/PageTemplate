package tang.chinwe.lib_page.page

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.FragmentActivity
import tang.chinwe.lib_page.lifecycle.DefaultActivityLifecycleCallbacks

/**
 * [Activity]ToolBar统一配置
 */
class PageActivityLifecycleCallbacksImpl :
        DefaultActivityLifecycleCallbacks {

    companion object {
        private val handler = Handler(Looper.getMainLooper())
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activity?.let {
            handler.post {
                PageManager.pageInit(it as? IPage<IPageView, IPageCallBack<IPageView>>)
            }
        }
        (activity as? FragmentActivity)?.supportFragmentManager?.registerFragmentLifecycleCallbacks(
                PageFragmentLifecycleCallbacksImpl(),
                true
        )
    }
}