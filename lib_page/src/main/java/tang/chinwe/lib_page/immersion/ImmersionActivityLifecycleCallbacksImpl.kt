package tang.chinwe.lib_page.immersion

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import tang.chinwe.lib_page.lifecycle.DefaultActivityLifecycleCallbacks

/**
 * [Activity]沉浸式状态栏统一处理
 * [Fragment]处理起来比较碎片化，框架不提供支持
 */
class ImmersionActivityLifecycleCallbacksImpl :
    DefaultActivityLifecycleCallbacks {

    companion object{
        private val handler=Handler(Looper.getMainLooper())
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activity?.let {
            handler.post {
                /**
                 * 可能会用到toolBar，所以丢到队尾执行，确保initView完成
                 */
                ImmersionManager.initImmersionBar(it)
            }
        }
    }
}