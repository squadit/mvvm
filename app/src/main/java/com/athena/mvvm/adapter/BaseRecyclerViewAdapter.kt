package com.athena.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.athena.mvvm.BR

abstract class BaseRecyclerViewAdapter<ItemType, ViewModelType>(
    items: MutableList<ItemType>,
    val viewModel: ViewModelType
) : RecyclerView.Adapter<BaseRecyclerViewAdapter<ItemType, ViewModelType>.ViewHolder>() {

    var items: MutableList<ItemType> = items
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], viewModel)
    override fun getItemCount(): Int = items.size
    override fun getItemViewType(position: Int): Int = getLayoutIdForItem(position)
    protected abstract fun getLayoutIdForItem(position: Int): Int

    inner class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemType, viewModel: ViewModelType) {
            binding.setVariable(BR.item, item)
            binding.setVariable(BR.viewModel, viewModel)
            binding.executePendingBindings()
        }
    }
}