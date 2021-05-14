# WeatherApp
## About
WeatherApp is made for you to comfortably check realtime weather status of your desired place. The application is built around the [World Weather](https://www.worldweatheronline.com/) API's

## Screenshots

<img src="https://github.com/santimendon/WeatherApp/blob/master/screenshots/weatherapp_dashboard.jpg" width="250" height="500" hspace=10 vspace=10>

<img src="https://github.com/santimendon/WeatherApp/blob/master/screenshots/weatherapp_recent_searches.jpg" width="250" height="500" hspace=10 vspace=10>

<img src="https://github.com/santimendon/WeatherApp/blob/master/screenshots/weatherapp_search.jpg" width="250" height="500" hspace=10 vspace=10>


## Features
* The app lets you check the real-time weather of your selected place.
* Add a new place from search and set it as your primary location
* Readily available list of your previously searched places.


## Architecture & Components
* [MVVM](https://developer.android.com/jetpack/guide?gclid=CjwKCAjwv_iEBhASEiwARoemvJYflm0CSMY1cN5BjXlWgWirIJ38AlmxyIDzt3KtzzB_ZmB-kegx-RoCvYQQAvD_BwE&gclsrc=aw.ds)
* [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
* [Retrofit2](https://square.github.io/retrofit/)
* [Room](https://developer.android.com/jetpack/androidx/releases/room)
* [Coroutines](https://developer.android.com/kotlin/coroutines)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [Databinding](https://developer.android.com/topic/libraries/data-binding)
* [Kodein](https://kodein.org/Kodein-DI/index.html?latest/android)



## Permissions
* Full Network Access.


## Installation
1. cd ~/ProjectLocation/WeatherApp
2. git clone git@github.com:santimendon/WeatherApp.git
3. Visit the [link](https://www.worldweatheronline.com/developer/) to create World weather API key
4. Create a new file named apikey.properties in your project root location
5. Add your API key
```API_KEY="XXXXXXXXX_API_KEYXXXXXX"```
6. Build and Run your project
