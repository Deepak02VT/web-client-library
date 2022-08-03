package com.vtnetzwelt.reusable_component

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.vtnetzwelt.reusable_component.viewmodel.OkhttpActivityViewModel

class OkhttpTestSampleActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp_test_sample)
        val  viewModel = ViewModelProvider(this)[OkhttpActivityViewModel::class.java]
        //viewModel.testGetCallWithOutParameter()
        /*viewModel.testGetRequestWithParameter()*/
        /* viewModel.testPostCall()*/
        viewModel.testDeleteCall()
        //viewModel.testPutCall()
    }
}
