package com.smendon.android.weatherapp.view.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smendon.android.weatherapp.data.local.entities.SearchHistoryItem
import com.smendon.android.weatherapp.databinding.HistoryItemBinding
import com.smendon.android.weatherapp.util.ClickListener

/**
 * Created by Sanket Mendon on 2020-05-04,
 * sanket.mendon@gmail.com
 */

class HistoryAdapter(
    private var dataset: List<SearchHistoryItem>,
    private val mListener: ClickListener<SearchHistoryItem>?
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private var mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as SearchHistoryItem
            mListener?.onItemClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item)
    }

    fun submitList(updatedDataset: List<SearchHistoryItem>) {
        this.dataset = updatedDataset
    }

    override fun getItemCount(): Int = dataset.size

    inner class ViewHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchHistoryItem) {
            binding.historyitem = item
            binding.productItemClick = mListener
            binding.executePendingBindings()
        }
    }
}