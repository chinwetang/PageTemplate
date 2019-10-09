package tang.chinwe.lib_page.toolbar

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.widget.TextView
import tang.chinwe.lib_page.R
import tang.chinwe.lib_page.expand.gone

/**
 * ToolBar管理类
 */
object ToolBarManager : IInitToolBar {

    const val EXTRA_INIT_TOOLBAR = "isInitToolBar"

    //默认不开启
    var defaultIsToolBar = false

    //默认返回键icon
    var defaultBackIcon = R.mipmap.page_template_default_back_icon

    //默认DrawablePadding
    var defaultDrawablePadding = 4

    var defaultInitToolBar = object : IInitToolBar {
        override fun initToolBar(toolBar: IToolBar?) {
            if (toolBar == null)
                return
            val context: Context?
            val rootView: View?
            val defaultTitle: CharSequence?
            when (toolBar) {
                is Activity -> {
                    context = toolBar
                    rootView = toolBar.window.decorView
                    defaultTitle = toolBar.title
                }
                is Fragment -> {
                    context = toolBar.context
                    rootView = toolBar.view
                    defaultTitle = null
                }
                else -> {
                    return
                }
            }
            if (context == null || rootView == null)
                return
            val layout = rootView.findViewById<View>(R.id.page_template_toolbar_layout)
            val leftTextView = rootView.findViewById<TextView>(R.id.page_template_toolbar_left)
            val rightTextView = rootView.findViewById<TextView>(R.id.page_template_toolbar_right)
            val titleTextView = rootView.findViewById<TextView>(R.id.page_template_toolbar_title)
            if (toolBar.hideToolBar() || layout != null) {
                layout.gone()
                return
            }
            titleTextView?.apply {
                toolBar.titleText()?.let {
                    setText(it)
                } ?: defaultTitle?.let {
                    text = it
                }
            }
            leftTextView?.apply {
                toolBar.leftText()?.let {
                    setText(it)
                }

                when (toolBar.leftIcon()) {
                    null -> {
                        if (toolBar.leftText() == null)
                            defaultBackIcon
                        else
                            null
                    }
                    else -> {
                        toolBar.leftIcon()
                    }
                }?.let {
                    compoundDrawablePadding = toolBar.drawablePadding() ?: defaultDrawablePadding
                    setCompoundDrawables(context.resources.getDrawable(it).apply {
                        setBounds(0, 0, minimumWidth, minimumHeight)
                    }, null, null, null)
                }
            }
            rightTextView?.apply {
                toolBar.rightText()?.let {
                    setText(it)
                }
                toolBar.rightIcon()?.let {
                    compoundDrawablePadding = toolBar.drawablePadding() ?: defaultDrawablePadding
                    setCompoundDrawables(context.resources.getDrawable(it).apply {
                        setBounds(0, 0, minimumWidth, minimumHeight)
                    }, null, null, null)
                }
            }

        }
    }


    override fun initToolBar(toolBar: IToolBar?) {
        //一级控制，全局控制
        var isInitToolBar = defaultIsToolBar
        //二级控制，Class控制
        if (toolBar is IIsInitToolBar) {
            isInitToolBar = toolBar.initToolBar()
        }
        /**
         * 三级控制，Object控制
         */
        isInitToolBar = when (toolBar) {
            is Activity -> {
                toolBar.intent.getBooleanExtra(EXTRA_INIT_TOOLBAR, isInitToolBar).apply {
                    //用完就遗弃，避免脏数据
                    toolBar.intent.removeExtra(EXTRA_INIT_TOOLBAR)
                }
            }
            is Fragment -> {
                toolBar.arguments?.getBoolean(EXTRA_INIT_TOOLBAR, isInitToolBar)?.apply {
                    //用完就遗弃，避免脏数据
                    toolBar.arguments?.remove(EXTRA_INIT_TOOLBAR)
                } ?: isInitToolBar
            }
            else -> {
                isInitToolBar
            }
        }

        if (isInitToolBar) {
            if (toolBar is IInitToolBar) {
                //二级控制，class控制
                toolBar.initToolBar(toolBar)
            } else {
                //一级控制，全局
                defaultInitToolBar.initToolBar(toolBar)
            }
        }

    }
}