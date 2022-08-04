package com.vtnetzwelt.reusable_component

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vtnetzwelt.reusable_component.viewmodel.WebClientTestViewModel

class WebClientTestSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp_test_sample)
        val viewModel = ViewModelProvider(this)[WebClientTestViewModel::class.java]
        viewModel.testGetCallWithOutParameter()
        viewModel.testGetRequestWithParameter()
        viewModel.testPostCall()
        viewModel.testDeleteCall()
        viewModel.testPutCall()
    }
}
