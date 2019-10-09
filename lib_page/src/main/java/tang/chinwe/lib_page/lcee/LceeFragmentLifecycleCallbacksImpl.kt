package tang.chinwe.lib_page.lcee

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class LceeFragmentLifecycleCallbacksImpl : FragmentManager.FragmentLifecycleCallbacks() {
    companion object {
        private val handler = Handler(Looper.getMainLooper())
    }

    override fun onFragmentActivityCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        handler.post {
            /**
             * 可能会用到toolBar，所以丢到队尾执行，确保initView完成
             */
            LceeManager.lceeInit(f as? ILcee)
        }
    }
}