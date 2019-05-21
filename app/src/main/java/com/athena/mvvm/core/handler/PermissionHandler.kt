package com.athena.mvvm.core.handler

import com.athena.mvvm.core.callback.PermissionCallback

interface PermissionHandler {

    fun hasPermission(permission: String): Boolean

    fun requestPermission(permission: String, permissionCallback: PermissionCallback)
}