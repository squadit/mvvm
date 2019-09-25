package com.athena.mvvm.core.callback

interface PermissionCallback {

    fun onPermissionGranted()

    fun onPermissionDenied()

    fun onNeverAskAgain()

    fun onAlreadyGranted()
}