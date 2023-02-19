package es.ddonaire.touristicpoi.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class POIResponse(@Json(name = "list") val pois: List<POI>) {
}