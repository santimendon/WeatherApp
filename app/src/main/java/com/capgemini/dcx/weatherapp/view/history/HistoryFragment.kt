package com.capgemini.dcx.weatherapp.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.capgemini.dcx.weatherapp.R
import com.capgemini.dcx.weatherapp.data.local.entities.SearchHistoryItem
import com.capgemini.dcx.weatherapp.databinding.HistoryFragmentBinding
import com.capgemini.dcx.weatherapp.util.ClickListener
import com.capgemini.dcx.weatherapp.view.search.SearchFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * Created by Sanket Mendon on 2020-05-04,
 * sanket.mendon@gmail.com
 */

class HistoryFragment : Fragment(), KodeinAware, ClickListener<SearchHistoryItem> {

    override val kodein by kodein()

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private lateinit var viewModel: SharedViewModel
    private lateinit var historyAdapter: HistoryAdapter

    private var adapterDataset: List<SearchHistoryItem> = listOf()

    private val factory: SharedViewModelFactory by instance()

    private lateinit var binding: HistoryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HistoryFragmentBinding.inflate(inflater, container, false)
        historyAdapter = HistoryAdapter(adapterDataset, this)
        binding.rcvViewHistoryList.adapter = historyAdapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(requireActivity(), factory).get(SharedViewModel::class.java)

        binding.txtEditLocation.setOnClickListener {
            var transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, SearchFragment())
            transaction?.commit()
        }

        viewModel.searchHistory.observe(viewLifecycleOwner, Observer {
            historyAdapter.submitList(it)
            historyAdapter.notifyDataSetChanged()
        })
    }

    override fun onItemClick(item: SearchHistoryItem) {

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.updateHistoryItem(item)

        }
        activity?.finish()
    }
}