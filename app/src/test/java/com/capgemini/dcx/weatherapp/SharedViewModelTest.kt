package com.capgemini.dcx.weatherapp

import com.capgemini.dcx.weatherapp.data.local.entities.SearchHistoryItem
import com.capgemini.dcx.weatherapp.data.repositories.AppRepository
import com.capgemini.dcx.weatherapp.view.history.SharedViewModel
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
class SharedViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var sharedViewModel: SharedViewModel

    @Mock
    private lateinit var appRepository: AppRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sharedViewModel = SharedViewModel(appRepository)
    }

    @After
    fun cleanUp() {
    }

    /*
     * In case a user selects a search item, it should appear as the first item in their history listing
     */
    @Test
    fun givenAfterNewItemInsertion_whenFetch_shouldReturnAsFirstItem() {
        testScope.launch {
            sharedViewModel = SharedViewModel(appRepository)
            for (i in 1..5) {
                var itemSelected = getTestItem(i)
                sharedViewModel.saveSearchItem(itemSelected)
                val dbList = sharedViewModel.searchHistory.value
                assert(dbList!!.indexOf(itemSelected) == 1)
            }
        }
    }

    /*
     * To confirm users search record insertion
     */
    @Test
    fun givenAfterNewItemInsertion_whenFetch_historyShouldContainItem() {
        testScope.launch {
            sharedViewModel = SharedViewModel(appRepository)
            var itemSelected = getTestItem(1)
            sharedViewModel.saveSearchItem(itemSelected)
            val dbList = sharedViewModel.searchHistory.value
            assert(dbList!!.contains(itemSelected))
        }
    }

    fun getTestItem(i: Int): SearchHistoryItem {
        val label = "TEST DATA"
        return SearchHistoryItem(
            "$label $i",
            "$label $i",
            "$label $i",
            "$label $i",
            "$label $i",
            "$label $i",
            "$label $i"
        )
    }


}


