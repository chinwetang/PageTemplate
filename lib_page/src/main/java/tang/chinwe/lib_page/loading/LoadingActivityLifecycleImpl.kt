package tang.chinwe.lib_page.loading

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.FragmentActivity
import tang.chinwe.lib_page.lifecycle.DefaultActivityLifecycleCallbacks

/**
 * 统一加载loading配置
 */
class LoadingActivityLifecycleImpl : DefaultActivityLifecycleCallbacks {
    companion object {
        private val handler = Handler(Looper.getMainLooper())
    }
    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activity?.let {
            handler.post {
                LoadingManager.loadingInit(activity as? ILoad, activity)
            }
        }

        if (activity is ILoad) {

        }
        (activity as? FragmentActivity)?.supportFragmentManager?.registerFragmentLifecycleCallbacks(
            LoadingFragmentLifecycleImpl(),
            true
        )
    }

    override fun onActivityDestroyed(activity: Activity?) {
        (activity as? ILoad)?.bmDismiss()
    }

}