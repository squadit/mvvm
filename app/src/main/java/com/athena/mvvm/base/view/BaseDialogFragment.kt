package com.athena.mvvm.base.view

import com.athena.mvvm.base.viewmodel.BaseViewModel
import com.athena.mvvm.core.view.CoreDialogFragment

abstract class BaseDialogFragment<VM : BaseViewModel> : CoreDialogFragment() {

    //region fields

    lateinit var viewModel: VM

    //endregion
}