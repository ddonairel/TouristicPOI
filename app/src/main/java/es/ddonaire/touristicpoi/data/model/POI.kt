package es.ddonaire.touristicpoi.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @param id id of the POI
 * @param title title of the POI
 * @param geocoordinates geoCoordinates of the POI
 * @param image link of the POI's image
 */
@Entity(tableName = "poi_table")
data class POI(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "geocoordinates") val geocoordinates: String,
    @ColumnInfo(name = "image") val image: String
) {
}