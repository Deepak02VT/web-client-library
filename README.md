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