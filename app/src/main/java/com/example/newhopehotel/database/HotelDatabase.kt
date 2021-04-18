package com.example.newhopehotel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newhopehotel.data.RoomConverter


@Database(
    entities = [RegisterEntity::class, CheckInCheckOutEntity::class, RoomServiceEntity::class, CleaningListEntity::class, RoomToCleanEntity::class, FeedbackEntity::class],
    version = 20, exportSchema = false
)
@TypeConverters(RoomConverter::class)
abstract class HotelDatabase : RoomDatabase() {

    abstract fun cicoDao(): CheckInCheckOutDatabaseDao
    abstract fun registerDao(): RegisterDatabaseDao
    abstract fun roomServiceDao():RoomServiceDatabaseDao
    abstract fun cleaningListDao():CleaningListDatabaseDao
    abstract fun roomToCleanDao():RoomToCleanDatabaseDao
    abstract fun feedbackListDao():FeedbackDao

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