package tang.chinwe.lib_page.lcee

import android.view.View

interface ILceeShow {

    var pageLoadingView: View?
    var pageContentView: View?
    var pageEmptyView: View?
    var pageErrorView: View?

    var showState: ShowState?

    fun showLoading() {
        if (showState == ShowState.Loading)
            return
        showState = ShowState.Loading
    }

    fun showErrorView() {
        if (showState == ShowState.Error)
            return
        showState = ShowState.Error

    }

    fun showEmpty() {
        if (showState == ShowState.Loading)
            return
        showState = ShowState.Loading

    }

    fun showContent() {
        if (showState == ShowState.Empty)
            return
        showState = ShowState.Empty

    }
}

enum class ShowState {
    Loading, Content, Error, Empty;
}
