package com.example.newhopehotel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newhopehotel.data.RoomConverter


@Database(
    entities = [RegisterEntity::class, CheckInCheckOutEntity::class, MorningCallEntity::class, RoomServiceEntity::class, CleaningListEntity::class, RoomToCleanEntity::class, SelectedWorkerEntity::class, FeedbackEntity::class],
    version = 19, exportSchema = false
)
@TypeConverters(RoomConverter::class)
abstract class HotelDatabase : RoomDatabase() {

    abstract fun cicoDao(): CheckInCheckOutDatabaseDao
    abstract fun registerDao(): RegisterDatabaseDao
    abstract fun morningCallDao():MorningCallDatabaseDao
    abstract fun roomServiceDao():RoomServiceDatabaseDao
    abstract fun cleaningListDao():CleaningListDatabaseDao
    abstract fun roomToCleanDao():RoomToCleanDatabaseDao
    abstract fun selectedWorkerDao():SelectedWorkerDatabaseDao
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