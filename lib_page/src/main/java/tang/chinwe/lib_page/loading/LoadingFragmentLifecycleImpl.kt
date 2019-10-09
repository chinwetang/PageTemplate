package tang.chinwe.lib_page.loading

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * 统一加载loading配置
 */
class LoadingFragmentLifecycleImpl : FragmentManager.FragmentLifecycleCallbacks() {
    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        if (f is ILoad) {
            LoadingManager.loadingInit(f, f.context)
        }
    }
}
