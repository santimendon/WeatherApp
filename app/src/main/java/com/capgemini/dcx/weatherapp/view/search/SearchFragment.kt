package com.capgemini.dcx.weatherapp.view.search

import android.os.Bundle
import android.os.RemoteException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.capgemini.dcx.weatherapp.R
import com.capgemini.dcx.weatherapp.data.local.entities.transformToSearchHistory
import com.capgemini.dcx.weatherapp.data.remote.models.searchmodel.SearchItem
import com.capgemini.dcx.weatherapp.databinding.SearchFragmentBinding
import com.capgemini.dcx.weatherapp.util.ClickListener
import com.capgemini.dcx.weatherapp.util.NetworkConnectionException
import com.capgemini.dcx.weatherapp.view.history.SharedViewModel
import com.capgemini.dcx.weatherapp.view.history.SharedViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * Created by Sanket Mendon on 2020-05-03,
 * sanket.mendon@gmail.com
 */

class SearchFragment : Fragment(), KodeinAware, ClickListener<SearchItem>,
    SearchView.OnQueryTextListener {

    override val kodein by kodein()

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SharedViewModel
    private val factory: SharedViewModelFactory by instance()
    private lateinit var binding: SearchFragmentBinding
    private lateinit var searchAdapter: SearchAdapter
    private var adapterDataset: List<SearchItem> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        searchAdapter = SearchAdapter(adapterDataset, this)
        binding.adapter = searchAdapter
        binding.rcvSearchList.adapter = searchAdapter
        binding.searchCityInput.setOnQueryTextListener(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(SharedViewModel::class.java)

        viewModel.searchData.observe(viewLifecycleOwner, Observer {
            it.search_api?.let {
                displayResultsView()
                renderList(it.result)
            }
            it.data?.let {
                displayErrorView(it.error.get(0).msg)
            }
        })
    }

    private fun renderList(results: List<SearchItem>) {
        adapterDataset = results
        searchAdapter.submitList(adapterDataset)
        searchAdapter.notifyDataSetChanged()
    }

    private fun displayResultsView() {
        binding.labelPageError.visibility = View.GONE
        binding.rcvSearchList.visibility = View.VISIBLE
    }

    private fun displayErrorView(error: String = "Something went wrong") {
        binding.labelPageError.visibility = View.VISIBLE
        binding.labelPageError.text = error
        binding.rcvSearchList.visibility = View.GONE
    }

    suspend fun setError(error: String) {
        withContext(Dispatchers.Main) {
            displayErrorView(error)
        }
    }

    override fun onItemClick(item: SearchItem) {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.saveSearchItem(transformToSearchHistory(item))
        }
        activity?.finish()

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        search(query.toString())
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query?.length!! >= 3) search(query.toString()) else displayErrorView(
            resources.getString(
                R.string.label_page_error
            )
        )
        return true
    }

    private fun search(searchQuery: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                viewModel.searchCity(searchQuery)

            } catch (e: RemoteException) {
                e.printStackTrace()
                println("RemoteException")
                setError(e.message.toString())

            } catch (e: NetworkConnectionException) {
                e.printStackTrace()
                println("RemoteException")
                setError(e.message.toString())
            }
        }
    }

}