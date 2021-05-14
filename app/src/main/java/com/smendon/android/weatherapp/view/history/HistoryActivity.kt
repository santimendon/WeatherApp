package com.smendon.android.weatherapp.view.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smendon.android.weatherapp.R

/**
 * Created by Sanket Mendon on 2020-05-04,
 * sanket.mendon@gmail.com
 */

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HistoryFragment.newInstance())
                .commitNow()
        }
    }
}