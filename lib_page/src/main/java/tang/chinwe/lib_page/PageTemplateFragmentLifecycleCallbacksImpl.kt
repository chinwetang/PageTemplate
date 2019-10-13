package tang.chinwe.lib_page

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class PageTemplateFragmentLifecycleCallbacksImpl : FragmentManager.FragmentLifecycleCallbacks(), LifecycleObserver {

    override fun onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        (f as? LifecycleOwner)?.lifecycle?.addObserver(PageTemplateLifecycleObserver)
    }

}