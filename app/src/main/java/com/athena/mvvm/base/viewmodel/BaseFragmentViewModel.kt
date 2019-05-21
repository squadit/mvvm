package com.athena.mvvm.base.viewmodel

open class BaseFragmentViewModel : BaseViewModel() {

    //region lifecycle

    open fun onCreate() {}
    open fun onCreateView() {}
    open fun onActivityCreated() {}
    open fun onStart() {}
    open fun onResume() {}
    open fun onPause() {}
    open fun onStop() {}
    open fun onDestroyView() {}
    open fun onDestroy() {}
    open fun onDetach() {}

    //endregion

}