package tang.chinwe.lib_page.loading

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import tang.chinwe.lib_page.lifecycle.DefaultActivityLifecycleCallbacks

/**
 * 统一加载loading配置
 */
class LoadingActivityLifecycleImpl : DefaultActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        if (activity is ILoad) {
            LoadingManager.loadingInit(activity, activity)
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