package com.athena.mvvm.core.view

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.athena.mvvm.core.callback.PermissionCallback
import com.athena.mvvm.core.handler.DisplayHandler
import com.athena.mvvm.core.handler.OrientationHandler
import com.athena.mvvm.core.handler.PermissionHandler
import com.athena.mvvm.core.handler.SoftInputHandler

open class CoreActivity : AppCompatActivity(), OrientationHandler, SoftInputHandler, DisplayHandler, PermissionHandler {

    //region fields

    private var permissionCallback: PermissionCallback? = null

    //endregion

    //region orientation

    override fun setOrientationToPortrait() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun setOrientationToLandscape() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    //endregion

    //region soft input

    override fun showSoftInput() {
        var view = currentFocus
        if (view == null) view = View(this)
        val imm = (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?) ?: return
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    override fun hideSoftInput() {
        var view = currentFocus
        if (view == null) view = View(this)
        val imm = (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?) ?: return
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    //endregion

    //region display

    override fun getDisplayWidth(): Int = resources.displayMetrics.widthPixels

    override fun getDisplayHeight(): Int = resources.displayMetrics.heightPixels

    //endregion

    //region permission

    override fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun requestPermission(permission: String, permissionCallback: PermissionCallback) {
        this.permissionCallback = permissionCallback
        if (hasPermission(permission)) {
            this.permissionCallback?.onPermissionGranted()
            this.permissionCallback = null
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(permission), 200)
        }
    }

    //endregion

    //region navigation

    fun addFragment(containerViewId: Int, fragment: Fragment, tag: String? = null) {
        supportFragmentManager
            .beginTransaction()
            .add(containerViewId, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    fun replaceFragment(containerViewId: Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(containerViewId, fragment)
            .commit()
    }

    //endregion

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            permissionCallback?.onPermissionGranted()
            permissionCallback = null
        } else {
            permissionCallback?.onPermissionDenied()
            permissionCallback = null
        }
    }
}