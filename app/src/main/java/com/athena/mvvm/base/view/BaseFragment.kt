package com.athena.mvvm.base.view

import android.os.Bundle
import com.athena.mvvm.base.viewmodel.BaseViewModel
import com.athena.mvvm.core.view.CoreFragment

abstract class BaseFragment<VM : BaseViewModel> : CoreFragment() {

    //region fields

    protected lateinit var viewModel: VM

    //endregion

    //region lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getVM()
    }

    //endregion

    //region abstract methods

    abstract fun getVM(): VM

    //endregion

}