package com.smendon.android.weatherapp.view.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smendon.android.weatherapp.data.remote.models.searchmodel.SearchItem
import com.smendon.android.weatherapp.databinding.SearchItemBinding
import com.smendon.android.weatherapp.util.ClickListener

/**
 * Created by Sanket Mendon on 2020-05-03,
 * sanket.mendon@gmail.com
 */

class SearchAdapter(
    private var dataset: List<SearchItem>,
    private val mListener: ClickListener<SearchItem>?
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as SearchItem
            mListener?.onItemClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item)
    }

    fun submitList(updatedDataset: List<SearchItem>) {
        this.dataset = updatedDataset
    }

    override fun getItemCount(): Int = dataset.size

    inner class ViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchItem) {
            binding.searchItem = item
            binding.productItemClick = mListener
            binding.executePendingBindings()
        }
    }
}