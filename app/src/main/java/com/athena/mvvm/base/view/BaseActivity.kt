package com.athena.mvvm.base.view

import android.os.Bundle
import com.athena.mvvm.base.viewmodel.BaseActivityViewModel
import com.athena.mvvm.core.view.CoreActivity

abstract class BaseActivity<VM : BaseActivityViewModel> : CoreActivity() {

    //region fields

    protected var viewModel: VM? = null

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