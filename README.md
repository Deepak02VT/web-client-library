
## WebClient

Wrapper class used to consume web API in the android application using okhttp library by squareup.

## Features

* Consume GET,POST,DELETE,PUT Http request type.
* Add a list of Headers.
* Configure connection time for each Http request.
* Can set global configuration to the okhttp client like connection time out,enable logging.

## How to integrate:

Add it to app level build.gradle:

```
  dependencies{
       implementation project(':base')
    }
    
```

## Use case in kotlin

## Global Configuration

``` 

  WebClientConfiguration.builder(this)
                        .connectionTimeOut(60)
                        .enableLogging(true)
                        .config()

```

## Request type GET without parameters

``` 

 WebConnect.get().endPoint(endPointUrl)
            .connect()

```

## Request type GET with parameters

``` 

  WebConnect.get(queryParam)
            .endPoint(endPointUrl)
            .connect()

```

## Request type GET with parameters

``` 

  WebConnect.get(queryParam)
            .endPoint(endPointUrl)
            .connect()

```

## Request type POST

``` 

 WebConnect.post(data.toString())
           .endPoint(Url)
           .connect()

```

## Request type PUT

``` 

  WebConnect.put(data)
            .endPoint(Url)           
            .connect()

```

## Request type DELETE

``` 

  WebConnect.delete(data)
            .endPoint(Url)
            .connect()
```

## Other methods

## Callbacks

``` 

 .setResponseCallback(object : ResponseCallback {
                override fun <T> onSuccess(data: T) {
                   
                }

                override fun onFailure(error: Throwable) {
                   
                }
            })
```

## Enable logging

``` 

   .enableLogging(true) - if set then logging will enable 
```

## Connection time out for all HttpRequest

``` 

 .connectionTimeOut(60) - By default 10 sec 
```

## Header

``` 

 headers(HashMap<String,String>)
```

## Connection time out for single HttpRequest

``` 

 setConnectionTimeOut(timeInSec) - By default 10 sec
```
