package com.athena.mvvm.core.view

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.athena.mvvm.core.callback.PermissionCallback
import com.athena.mvvm.core.handler.DisplayHandler
import com.athena.mvvm.core.handler.OrientationHandler
import com.athena.mvvm.core.handler.PermissionHandler
import com.athena.mvvm.core.handler.SoftInputHandler

open class CoreFragment : Fragment(), OrientationHandler, SoftInputHandler, PermissionHandler, DisplayHandler {

    //region fields

    private var permissionCallback: PermissionCallback? = null
    private lateinit var softInputHandler: SoftInputHandler
    private lateinit var orientationHandler: OrientationHandler
    private lateinit var displayHandler: DisplayHandler

    //endregion

    //region lifecycle

    override fun onAttach(context: Context) {
        super.onAttach(context)
        softInputHandler = context as SoftInputHandler
        orientationHandler = context as OrientationHandler
        displayHandler = context as DisplayHandler
    }

    //endregion

    //region orientation

    override fun setOrientationToPortrait() = orientationHandler.setOrientationToPortrait()

    override fun setOrientationToLandscape() = orientationHandler.setOrientationToLandscape()

    //endregion

    //region soft input

    override fun showSoftInput() = softInputHandler.showSoftInput()

    override fun hideSoftInput() = softInputHandler.hideSoftInput()

    //endregion

    //region display

    override fun getDisplayWidth(): Int = displayHandler.getDisplayWidth()

    override fun getDisplayHeight(): Int = displayHandler.getDisplayHeight()

    //endregion

    //region permission

    override fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context!!, permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun requestPermission(permission: String, permissionCallback: PermissionCallback) {
        this.permissionCallback = permissionCallback
        if (hasPermission(permission)) {
            this.permissionCallback?.onPermissionGranted()
            this.permissionCallback = null
        } else {
            requestPermissions(arrayOf(permission), 200)
        }
    }

    //endregion

    //region navigation

    fun addFragment(containerViewId: Int, fragment: Fragment, tag: String? = null) {
        childFragmentManager
            .beginTransaction()
            .add(containerViewId, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    fun replaceFragment(containerViewId: Int, fragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .replace(containerViewId, fragment)
            .commit()
    }

    //endregion

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            permissionCallback?.onPermissionGranted()
            permissionCallback = null
        } else {
            permissionCallback?.onPermissionDenied()
            permissionCallback = null
        }
    }

}