package tang.chinwe.lib_page.loading;

public interface ILoading {
    /**
     * 显示加载
     */
    default void bmShow() {

    }

    /**
     * 隐藏加载
     */
    default void bmDismiss() {

    }

    /**
     * 设置文本
     */
    default void setMessage(String message) {

    }
}
