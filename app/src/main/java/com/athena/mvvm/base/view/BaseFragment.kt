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
        viewModel?.onCreate()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel?.onActivityCreated()
    }

    override fun onStart() {
        super.onStart()
        viewModel?.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel?.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel?.onPause()
    }

    override fun onStop() {
        super.onStop()
        viewModel?.onStop()
    }

    override fun onDetach() {
        super.onDetach()
        viewModel?.onDetach()
    }

    //endregion

    //region abstract methods

    abstract fun getVM(): VM

    //endregion

}