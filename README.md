# TouristicPOI

## Description
The test consists in developing a client app that shows a set of touristic POIs (Points of
Interest) with some details.
The list of POIs can be found in the following URL:
* https://sergiocasero.es/pois.json

## Minimum requirements
* The data obtained has to be persistent. If the data has been requested before,
it should be stored and available to retrieve from database the next time that it
will be requested.
* The app must show the list of POIs. The format in which they are displayed is
free.
* The user must be able to select a POI and see its detail.
* The user must be able to search & filter POIs.

## Criteria for evaluation
* User interface & usability are open, but they must follow the directives of the
platform and they must also have an updated style and be appropriate for the
shown data and the desired functionalities.
* Libraries used. It is possible to use own or third-party libraries if it is justified,
reduce development time and do not reduce final quality of the app. Correct
election of libraries will be evaluated.
* Code Quality. The code must be legible, clear and documented.
* Architecture. The app must have an architecture coherent with the platform.
* Extras. Any additional functionality will be positively evaluated if it is coherent
with the rest of the app and it has sense in the context of the app.

### App

Touristic POI is an application that uses the MVVM pattern with the Repository pattern. Although it is not a complicated test, it took a few hours to have a minimum viable product.

### Instalation

1. Open Android Studio
2. Download as a zip the project or clone it with the command $ git clone https://github.com/ddonairel/TouristicPOI.git.
3. Once the project is opened, go to "File" -> "Sync Project with gradle files"
4. Click on "Run 'app'" button to install the project on your phone or emulator

### Libraries

* Retrofit - Type-safe HTTP client forAndroid.
* Moshi - JSON library for Android, Java and Kotlin used to parse JSON into Kotlin classes.
* Room - Persistance library for local database storage.
* Glide - Library to load images.
* Logging Interceptor - Library that logs HTTP request and response data.

