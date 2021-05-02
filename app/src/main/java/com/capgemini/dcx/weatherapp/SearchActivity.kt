package com.capgemini.dcx.assisgnment

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.capgemini.dcx.assisgnment.data.local.entities.transformToSearchHistory
import com.capgemini.dcx.assisgnment.data.remote.models.searchmodel.SearchItem
import com.capgemini.dcx.assisgnment.view.search.ClickListener
import com.capgemini.dcx.assisgnment.view.search.SearchCityAdapter
import com.capgemini.dcx.assisgnment.view.search.SearchViewModel
import com.capgemini.dcx.assisgnment.view.search.SearchViewModelFactory
import com.capgemini.dcx.weatherapp.databinding.ActivitySearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

@InternalCoroutinesApi
class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener, KodeinAware,
    ClickListener<SearchItem> {
    override val kodein by kodein()
    private lateinit var binding: ActivitySearchBinding

    private lateinit var viewModel: SearchViewModel
    private val factory: SearchViewModelFactory by instance()

    private var adapterDataset: List<SearchItem> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchcityAdapter = SearchCityAdapter(adapterDataset, this)
        binding.adapter = searchcityAdapter

        binding.searchCityInput.setOnQueryTextListener(this)

        viewModel = ViewModelProviders.of(this, factory).get(SearchViewModel::class.java)
        viewModel.searchData.observe(this, Observer {
            Log.d("TAG", "Response: ${it.toString()}")
            searchcityAdapter.submitList(it.search_api.result)
            searchcityAdapter.notifyDataSetChanged()
        })

        viewModel.searchHistory.observe(this, Observer {
            Log.d("TAG", "From db: $it")
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.d("TAG", "onQueryTextSubmit ${query.toString()}")
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.searchCity(query.toString())
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return true
    }

    override fun onItemClick(item: SearchItem) {
        Log.d("TAG", "Clicked item: $item")
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.saveSearchItem(transformToSearchHistory(item))
        }
    }
}