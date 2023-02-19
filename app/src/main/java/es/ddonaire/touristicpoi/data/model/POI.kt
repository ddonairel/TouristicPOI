package es.ddonaire.touristicpoi.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poi_table")
data class POI(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "geocoordinates") val geocoordinates: String,
    @ColumnInfo(name = "image") val image: String
) {
}