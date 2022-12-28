package com.vtnra.webclient


/**
 * To build web client builder and pass data to the related class
 */
object WebConnect
{
    fun get():WebClientBuilder{
        return WebClientBuilder("get")
    }

    fun get(queryParam:Map<String,String>):WebClientBuilder{
        return WebClientBuilder("get",queryParam,false)
    }

    fun post(requestData:String):WebClientBuilder{
        return WebClientBuilder("post",requestData)
    }

    fun post(requestData:Map<String,Any>,formEncode:Boolean):WebClientBuilder{
        return WebClientBuilder("post",requestData,formEncode)
    }

    fun delete(requestData:String):WebClientBuilder{
        return WebClientBuilder("delete",requestData)
    }

    fun put(requestData: String):WebClientBuilder{
        return WebClientBuilder("put",requestData)
    }
}