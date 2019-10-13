package tang.chinwe.lib_page.loading

import android.arch.lifecycle.LifecycleOwner
import android.content.Context

interface IInitLoading {
    fun loadingInit(owner: LifecycleOwner?){}
    fun loadingInit(load: ILoad?, context: Context?){}
}
