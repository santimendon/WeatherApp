package com.capgemini.dcx.assisgnment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.capgemini.dcx.assisgnment.view.search.SearchViewModel
import com.capgemini.dcx.assisgnment.view.search.SearchViewModelFactory
import com.capgemini.dcx.weatherapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {
    private lateinit var viewModel: SearchViewModel
    override val kodein by kodein()

    private val factory: SearchViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, factory).get(SearchViewModel::class.java)

        btn_search.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.searchCity(edt_search_query.text.toString())
            }
        }


        viewModel.searchData.observe(this, Observer {
            Log.d("TAG", it.toString())
        })
    }
}