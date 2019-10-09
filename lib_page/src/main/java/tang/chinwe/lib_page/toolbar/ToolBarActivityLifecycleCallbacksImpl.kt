package tang.chinwe.lib_page.toolbar

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.FragmentActivity
import android.widget.Toolbar
import tang.chinwe.lib_page.lifecycle.DefaultActivityLifecycleCallbacks

/**
 * [Activity]沉浸式状态栏统一处理
 */
class ToolBarActivityLifecycleCallbacksImpl :
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
                ToolBarManager.initToolBar(it as? IToolBar)
            }
        }
        (activity as? FragmentActivity)?.supportFragmentManager?.registerFragmentLifecycleCallbacks(
            ToolBarFragmentLifecycleCallbacksImpl(),
            true
        )
    }
}