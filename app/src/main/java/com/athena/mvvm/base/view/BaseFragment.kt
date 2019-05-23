package com.athena.mvvm.base.view

import android.os.Bundle
import com.athena.mvvm.base.viewmodel.BaseFragmentViewModel
import com.athena.mvvm.core.view.CoreFragment

abstract class BaseFragment<VM : BaseFragmentViewModel> : CoreFragment() {

    //region fields

    protected var viewModel: VM? = null

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