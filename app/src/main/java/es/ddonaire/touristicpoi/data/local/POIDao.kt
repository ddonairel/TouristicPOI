package es.ddonaire.touristicpoi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.ddonaire.touristicpoi.data.model.POI

@Dao
interface POIDao {

    /**
     * Insert a POI. If the POI already exists, replace it.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPOI(poi: POI)

    /**
     * Insert multiple POIs.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPOIs(poi: List<POI>)

    /**
     * @return all POIs
     */
    @Query("SELECT * FROM poi_table")
    suspend fun getAllPOIs(): List<POI>

    /**
     * @param id the id of the POI
     * @return the POI with the id
     */
    @Query("SELECT * FROM poi_table WHERE id = :id")
    suspend fun getPOI(id: Int): POI?

    /**
     * @param title title of the POI
     * @return POIs that contain the input string in the title
     */
    @Query("SELECT * FROM poi_table WHERE title LIKE '%' || :title || '%'")
    suspend fun getPOIsbyTitle(title: String): List<POI>

    /**
     * @param id the id of the POI
     */
    @Query("DELETE FROM poi_table where id = :id")
    suspend fun deletePOI(id: Int)

    /**
     * Delete all POIs
     */
    @Query("DELETE FROM poi_table")
    suspend fun deleteAllPOIs()

}