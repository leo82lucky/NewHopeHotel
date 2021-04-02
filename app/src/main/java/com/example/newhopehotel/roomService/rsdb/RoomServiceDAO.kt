package com.example.newhopehotel.roomService.rsdb

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface RoomServiceDAO  {

    @Insert
    suspend fun insertRoomService(roomService: RoomService) : Long

    @Update
    suspend fun updateRoomService(roomService: RoomService) : Int

    @Delete
    suspend fun deleteRoomService(roomService: RoomService) : Int

    @Query("DELETE FROM room_service_data_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM room_service_data_table")
    fun getAllRoomService(): LiveData<List<RoomService>>
}