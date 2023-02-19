package es.ddonaire.touristicpoi.repository

import es.ddonaire.touristicpoi.data.local.POIDao
import es.ddonaire.touristicpoi.data.model.POI
import es.ddonaire.touristicpoi.data.remote.POIApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class POIRepository(private val dataSource: POIDao) {

    suspend fun getPOIs(): List<POI> = withContext(Dispatchers.IO) {
        return@withContext try {
            dataSource.getAllPOIs()
        } catch (e: Exception) {
            ArrayList<POI>()
        }
    }

    suspend fun getPOIsByTitle(title: String): List<POI> = withContext(Dispatchers.IO) {
        return@withContext dataSource.getPOIsByTitle(title)
    }

    suspend fun getPOI(id: Int): POI? = withContext(Dispatchers.IO) {
        return@withContext dataSource.getPOI(id)
    }

    suspend fun downloadPOIs() = withContext(Dispatchers.IO) {
        try {
            val result = POIApi.retrofitService.getPOIs()
            dataSource.insertPOIs(result.pois)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}