## ImageLoader

Wrapper class to load image in the android application using Glide.

## Features

* Load image from Url, file, Uri and to load drawable from the app.
* Enable/disable cache.
* Resize the image.
* Can set loading and error image.

## How to integrate:

Add it to app level build.gradle:

```
  dependencies{
       implementation project(':base')
    }
    
```

## Use case in kotlin

## General Use

``` 
ImageView.load(url)

ImageLoader.with(url)
           .scaleType(ImageView.ScaleType.CENTER_CROP)
           .on(ImageView)
           .load() 

```

## To load from Url

```
ImageView.load(url)
```

## To load form File/Camera/Gallery

```
ImageView.load(file)

```

## To load from Uri/Gallery

```
ImageView.load(uri)

```

## To load form drawable

```
ImageView.load(R.drawable.name)

```

## Scale Type

``` 
ImageView.load(
            R.drawable.name,
            scaleType = ImageView.ScaleType.CENTER_INSIDE
        )
```

## Resize

```
ImageView.load(
            R.drawable.name,
            height = 100,
            width = 100
        )
```

## Enable cache

```
ImageView.load(
            url,
            enableCache = true
        )
```

## To show error image and loading image

```
ImageView.load(
            url,
            loadingImage=R.drawable.loadingImage,
            errorImage=R.drawable.errorImage
        )

```

## Use case in java

```
ImageLoader.with(this,url/file/uri/drawable)
           .enableCache(true)
           .on(ImageView)
           .load() 
```

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
