package tang.chinwe.lib_page.lcee

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import tang.chinwe.lib_page.R
import tang.chinwe.lib_page.expand.gone
import tang.chinwe.lib_page.expand.visible

/**
 * 默认策略
 */
class DefaultLceeShow(
        override var pageLoadingView: View?,
        override var pageContentView: View?,
        override var pageEmptyView: View?,
        override var pageErrorView: View?,
        override var showState: ShowState? = ShowState.Content
) : ILceeShow {
    init {
        pageLoadingView?.visible(showState == ShowState.Loading)
        pageContentView?.visible(showState == ShowState.Content)
        pageEmptyView?.visible(showState == ShowState.Empty)
        pageErrorView?.visible(showState == ShowState.Error)
    }

    override fun showLoading() {
        super.showLoading()
        pageLoadingView?.visible()
        pageContentView?.gone()
        pageEmptyView?.gone()
        pageErrorView?.gone()
    }

    override fun showEmpty() {
        super.showEmpty()
        pageLoadingView?.gone()
        pageContentView?.gone()
        pageEmptyView?.visible()
        pageErrorView?.gone()
    }

    override fun showErrorView() {
        super.showErrorView()
        pageLoadingView?.gone()
        pageContentView?.gone()
        pageEmptyView?.gone()
        pageErrorView?.visible()
    }

    private val dp_50 by lazy {
        val resource = pageLoadingView?.resources ?: pageContentView?.resources
        return@lazy resource?.getDimension(R.dimen.page_height_50dp) ?: 100f
    }

    private val duration by lazy {
        val resource = pageLoadingView?.resources ?: pageContentView?.resources
        return@lazy resource?.getInteger(R.integer.lce_content_view_show_animation_time)?.toLong()
            ?: 100L
    }

    override fun showContent() {
        super.showContent()
        if (showState == ShowState.Loading && pageLoadingView != null && pageContentView != null) {
            pageEmptyView?.gone()
            pageErrorView?.gone()

            val set = AnimatorSet()
            val contentFadeIn = ObjectAnimator.ofFloat(
                pageContentView,
                "alpha", 0f, 1f
            )
            val contentTranslateIn = ObjectAnimator.ofFloat(
                pageContentView, "translationY", dp_50, 0f
            )

            val loadingFadeOut = ObjectAnimator.ofFloat(
                pageLoadingView,
                "alpha", 1f, 0f
            )
            val loadingTranslateOut = ObjectAnimator.ofFloat(
                pageLoadingView, "translationY", 0f, -dp_50
            )

            set.playTogether(
                contentFadeIn, contentTranslateIn, loadingFadeOut,
                loadingTranslateOut
            )
            set.duration = duration

            set.addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationStart(animation: Animator) {
                    pageContentView?.translationY = 0f
                    pageLoadingView?.translationY = 0f
                    pageContentView?.visible()
                }

                override fun onAnimationEnd(animation: Animator) {
                    pageLoadingView?.gone()
                    pageLoadingView?.alpha = 1f
                    pageContentView?.translationY = 0f
                    pageLoadingView?.translationY = 0f
                }
            })

            set.start()
        } else {
            pageLoadingView?.gone()
            pageContentView?.visible()
            pageEmptyView?.gone()
            pageErrorView?.gone()
        }
    }
}