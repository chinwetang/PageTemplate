package tang.chinwe.lib_page

import android.app.Activity
import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import tang.chinwe.lib_page.lifecycle.DefaultActivityLifecycleCallbacks

class PageTemplateActivityLifecycleCallbacksImpl :
        DefaultActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        (activity as? FragmentActivity)?.supportFragmentManager?.registerFragmentLifecycleCallbacks(
                PageTemplateFragmentLifecycleCallbacksImpl(),
                true)
        (activity as? LifecycleOwner)?.lifecycle?.addObserver(PageTemplateLifecycleObserver)
    }
}