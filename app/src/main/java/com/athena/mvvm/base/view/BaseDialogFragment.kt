package com.athena.mvvm.base.view

import com.athena.mvvm.base.viewmodel.BaseDialogFragmentViewModel
import com.athena.mvvm.core.view.CoreDialogFragment

abstract class BaseDialogFragment<VM : BaseDialogFragmentViewModel> : CoreDialogFragment() {

    //region fields

    protected var viewModel: VM? = null

    //endregion
}