package es.ddonaire.touristicpoi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.ddonaire.touristicpoi.data.model.POI

@Database(entities = [POI::class], version = 1, exportSchema = false)
abstract class POIDatabase: RoomDatabase() {

    abstract val poiDao: POIDao

    companion object {

        @Volatile
        private var INSTANCE: POIDatabase? = null

        fun getInstance(context: Context): POIDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        POIDatabase::class.java,
                        "poi_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance

                }
                return instance
            }
        }
    }

}