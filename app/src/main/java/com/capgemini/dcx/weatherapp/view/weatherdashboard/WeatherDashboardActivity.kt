package com.capgemini.dcx.weatherapp.view.weatherdashboard

import android.R
import android.content.Intent
import android.os.Bundle
import android.os.RemoteException
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.capgemini.dcx.weatherapp.databinding.ActivityWeatherDashboardBinding
import com.capgemini.dcx.weatherapp.util.NetworkConnectionException
import com.capgemini.dcx.weatherapp.view.history.HistoryActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


/**
 * Created by Sanket Mendon on 2020-05-01,
 * sanket.mendon@gmail.com
 */
class WeatherDashboardActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()

    private lateinit var binding: ActivityWeatherDashboardBinding
    private lateinit var viewModel: WeatherDashboardViewModel
    private val factory: WeatherDashboardModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this, factory).get(WeatherDashboardViewModel::class.java)

        binding.weatherViewModel = viewModel
        binding.lifecycleOwner = this

        val dailyWeatherAdapter = DailyWeatherItemAdapter()
        binding.dailyWeatherAdapter = dailyWeatherAdapter

        val monthlyWeatherAdapter = MonthlyWeatherItemAdapter()
        binding.monthlyWeatherAdapter = monthlyWeatherAdapter

        binding.textPlace.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val currentLocation = viewModel.getUserLocation()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                currentLocation?.let { viewModel.getCurrentWeather(it) }

            } catch (e: RemoteException) {
                e.printStackTrace()
                setError(e.message.toString())

            } catch (e: NetworkConnectionException) {
                e.printStackTrace()
                setError(e.message.toString())
            }

        }

    }

    suspend fun setError(error: String) {
        withContext(Dispatchers.Main) {
            val snackbar = Snackbar.make(
                findViewById(R.id.content),
                error,
                Snackbar.LENGTH_LONG
            )
            snackbar.show()
        }
    }
}