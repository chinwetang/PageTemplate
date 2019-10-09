package tang.chinwe.lib_page

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import tang.chinwe.lib_page.immersion.ImmersionActivityLifecycleCallbacksImpl
import tang.chinwe.lib_page.toolbar.ToolBarActivityLifecycleCallbacksImpl

class InitProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        (context?.applicationContext as Application).apply {
            registerActivityLifecycleCallbacks(ToolBarActivityLifecycleCallbacksImpl())
            registerActivityLifecycleCallbacks(ImmersionActivityLifecycleCallbacksImpl())
        }
        return true
    }


    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }

}