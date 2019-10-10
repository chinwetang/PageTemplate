package tang.chinwe.lib_page.lcee

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.FragmentActivity
import tang.chinwe.lib_page.lifecycle.DefaultActivityLifecycleCallbacks
import tang.chinwe.lib_page.page.PageFragmentLifecycleCallbacksImpl

/**
 * [Activity]ToolBar统一配置
 */
class LceeActivityLifecycleCallbacksImpl :
    DefaultActivityLifecycleCallbacks {

    companion object {
        private val handler = Handler(Looper.getMainLooper())
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activity?.let {
            handler.post {
                /**
                 * 可能会用到toolBar，所以丢到队尾执行，确保initView完成
                 */
                LceeManager.lceeInit(it as? ILcee)
            }
        }
        (activity as? FragmentActivity)?.supportFragmentManager?.registerFragmentLifecycleCallbacks(
            PageFragmentLifecycleCallbacksImpl(),
            true
        )
    }
}