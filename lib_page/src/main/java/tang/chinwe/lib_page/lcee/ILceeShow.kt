package tang.chinwe.lib_page.lcee

import android.view.View

interface ILceeShow {

    var loadingView: View?
    var contentView: View?
    var emptyView: View?
    var errorView: View?

    var state: ShowState

    fun showLoading() {
        if (state == ShowState.Loading)
            return
        state = ShowState.Loading
    }

    fun showErrorView() {
        if (state == ShowState.Error)
            return
        state = ShowState.Error

    }

    fun showEmpty() {
        if (state == ShowState.Loading)
            return
        state = ShowState.Loading

    }

    fun showContent() {
        if (state == ShowState.Empty)
            return
        state = ShowState.Empty

    }
}

enum class ShowState {
    Loading, Content, Error, Empty;
}
