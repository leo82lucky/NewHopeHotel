package com.example.newhopehotel.database

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [RegisterEntity::class, CheckInCheckOutEntity::class],
    version = 1, exportSchema = false
)
//@TypeConverters(Converters::class)
abstract class HotelDatabase : RoomDatabase() {

    abstract val registerDatabaseDao: RegisterDatabaseDao

    abstract val checkInCheckOutDatabaseDao: CheckInCheckOutDatabaseDao

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