package com.vtnetzwelt.reusable_component

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class WebClientTestSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp_test_sample)

        /*val jsonObject = JsonObject()
        jsonObject.addProperty(
            "awsUserId",
            "7810a74e-0e0f-4edc-8be2-9d0ed95d65a4"
        )
        jsonObject.addProperty("first", 0)
        jsonObject.addProperty("count", 10)
        WebConnect.post("client/logs",jsonObject.toString()){

            connect(object:OnWebConnectCallback{
                override fun onResponse(data: WebConnectNetworkResponseState, taskId:String) {
                   when(data){
                       is WebConnectNetworkResponseState.Success<*> ->{
                           Log.e("TAG", "onResponse: Success ${data.data} $taskId")
                       }
                       is WebConnectNetworkResponseState.Failure ->{
                           Log.e("TAG", "Failure: $taskId.${
                               data.error
                           }")
                       }
                       is WebConnectNetworkResponseState.Loading ->{
                           Log.e("TAG", "onResponse: Loading $taskId")
                       }
                       is WebConnectNetworkResponseState.Error -> {
                           Log.e("TAG", " Error $taskId ")
                       }
                   }
                }
            })
        }*/
    }
}
