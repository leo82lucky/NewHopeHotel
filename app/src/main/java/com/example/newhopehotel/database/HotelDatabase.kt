package com.example.newhopehotel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newhopehotel.data.RoomStatusConverter
import com.example.newhopehotel.data.RoomTypeConverter

@Database(
    entities = [RegisterEntity::class, CheckInCheckOutEntity::class],
    version = 2, exportSchema = false
)
@TypeConverters(RoomTypeConverter::class, RoomStatusConverter::class)
abstract class HotelDatabase : RoomDatabase() {

    //    abstract val registerDatabaseDao: RegisterDatabaseDao
//    abstract val checkInCheckOutDatabaseDao: CheckInCheckOutDatabaseDao
    abstract fun cicoDao(): CheckInCheckOutDatabaseDao
    abstract fun registerDao(): RegisterDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: HotelDatabase? = null


        fun getInstance(context: Context): HotelDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HotelDatabase::class.java,
                        "newHopeHotel_database",
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}