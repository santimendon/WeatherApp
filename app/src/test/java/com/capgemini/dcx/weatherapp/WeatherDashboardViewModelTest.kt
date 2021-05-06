package com.capgemini.dcx.weatherapp

import com.capgemini.dcx.weatherapp.data.repositories.AppRepository
import com.capgemini.dcx.weatherapp.view.weatherdashboard.WeatherDashboardViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherDashboardViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var dashboardViewModel: WeatherDashboardViewModel

    @Mock
    private lateinit var appRepository: AppRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dashboardViewModel = WeatherDashboardViewModel(appRepository)
    }

    @After
    fun cleanUp() {
    }

    /*
     * For results matching search query
     */
    @Test
    fun givenCorrectResult_whenFetch_fromRemote() {
        testScope.launch {
            dashboardViewModel = WeatherDashboardViewModel(appRepository)

            var fetchQuery = "New York"
            dashboardViewModel.getCurrentWeather(fetchQuery)
            dashboardViewModel.currentWeatherData.observeForever {}

            assertEquals(dashboardViewModel.currentWeatherData.value, fetchQuery)
        }
    }
}


