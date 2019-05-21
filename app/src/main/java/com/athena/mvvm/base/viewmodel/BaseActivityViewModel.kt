package com.athena.mvvm.base.viewmodel

open class BaseActivityViewModel : BaseViewModel() {

    //region lifecycle

    open fun onCreate() {}

    open fun onStart() {}

    open fun onResume() {}

    open fun onPause() {}

    open fun onStop() {}

    open fun onDestroy() {}

    //endregion

}