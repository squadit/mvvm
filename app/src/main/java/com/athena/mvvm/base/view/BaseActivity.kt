package com.athena.mvvm.base.view

import android.os.Bundle
import com.athena.mvvm.base.viewmodel.BaseViewModel
import com.athena.mvvm.core.view.CoreActivity

abstract class BaseActivity<VM : BaseViewModel> : CoreActivity() {

    //region fields

    lateinit var viewModel: VM

    //endregion

    //region lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getVM()
    }

    //endregion

    //region abstract

    abstract fun getVM(): VM

    //endregion
}