package com.example.newhopehotel.roomService.rsdb


class RoomServiceRepository(private val dao : RoomServiceDAO) {

    val roomService = dao.getAllRoomService()

    suspend fun insert(roomService: RoomService):Long{
        return dao.insertRoomService(roomService)
    }

    suspend fun update(roomService: RoomService):Int{
        return dao.updateRoomService(roomService)
    }

    suspend fun delete(roomService: RoomService) : Int{
        return dao.deleteRoomService(roomService)
    }

    suspend fun deleteAll() : Int{
        return dao.deleteAll()
    }
}