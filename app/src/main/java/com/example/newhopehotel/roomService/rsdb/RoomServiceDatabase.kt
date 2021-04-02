package com.example.newhopehotel.roomService.rsdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [RoomService::class],version = 1)
abstract class RoomServiceDatabase : RoomDatabase() {

    abstract val roomServiceDAO :RoomServiceDAO

    companion object{
        @Volatile
        private var INSTANCE : RoomServiceDatabase? = null
        fun getInstance(context: Context):RoomServiceDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomServiceDatabase::class.java,
                        "room_service_data_database"
                    ).build()
                }
                return instance
            }
        }

    }
}
