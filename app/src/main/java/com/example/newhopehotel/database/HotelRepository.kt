package com.example.newhopehotel.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.newhopehotel.data.RoomStatus
import com.example.newhopehotel.utils.AppExecutors

class HotelRepository private constructor(
//    private val registerDatabaseDao: RegisterDatabaseDao,
//    private val checkInCheckOutDatabaseDao: CheckInCheckOutDatabaseDao,
    private val hotelDatabase: HotelDatabase,
    private val mExecutors: AppExecutors
) {
    val users: LiveData<List<RegisterEntity>>
        get() = hotelDatabase.registerDao().allUsers

    val cicos: LiveData<List<CheckInCheckOutEntity>>
        get() = hotelDatabase.cicoDao().allCico
    val morningCalls: LiveData<List<MorningCallEntity>>
        get() = hotelDatabase.morningCallDao().allMorningCall
    val roomServices: LiveData<List<RoomServiceEntity>>
        get() = hotelDatabase.roomServiceDao().allRoomService
    //    val users = registerDatabaseDao.getAllUsers()
//    val customers = checkInCheckOutDatabaseDao.allCustomers
//    suspend fun insert(user: RegisterEntity) {
//        return hotelDatabase.registerDao().insert(user)
////        return registerDatabaseDao.insert(user)
//    }
    fun insert(user: RegisterEntity) {
        mExecutors.diskIO().execute { hotelDatabase.registerDao().insert(user) }
    }

    suspend fun getUserName(userName: String): RegisterEntity? {
        Log.i("MYTAG", "inside Repository GetUsers fun ")
        return hotelDatabase.registerDao().getUsername(userName)
//        return registerDatabaseDao.getUsername(userName)
    }

    val morningCallList: LiveData<List<MorningCallEntity>>
        get() = hotelDatabase.morningCallDao().allMorningCall

    fun getChosenMorningCall(mcId: Int): LiveData<MorningCallEntity> {
        return hotelDatabase.morningCallDao().getChosenMorningCall(mcId)
    }

    fun insertMorningCall(morningCall:MorningCallEntity) {
        mExecutors.diskIO().execute { hotelDatabase.morningCallDao().insertMorningCall(morningCall) }
    }

    fun updateMorningCall(morningCall: MorningCallEntity) {
        mExecutors.diskIO().execute { hotelDatabase.morningCallDao().updateMorningCall(morningCall) }
    }

    fun deleteMorningCall(morningCall: MorningCallEntity) {
        mExecutors.diskIO().execute { hotelDatabase.morningCallDao().deleteMorningCall(morningCall) }
    }

    val roomServiceList: LiveData<List<RoomServiceEntity>>
        get() = hotelDatabase.roomServiceDao().allRoomService

    fun getChosenRoomService(custId: Int): LiveData<RoomServiceEntity> {
        return hotelDatabase.roomServiceDao().getChosenRoomService(custId)
    }

    fun insertRoomService(roomService: RoomServiceEntity) {
        mExecutors.diskIO().execute { hotelDatabase.roomServiceDao().insertRoomService(roomService) }
    }

    fun updateRoomService(roomService: RoomServiceEntity) {
        mExecutors.diskIO().execute { hotelDatabase.roomServiceDao().updateRoomService(roomService) }
    }

    fun deleteRoomService(roomService: RoomServiceEntity) {
        mExecutors.diskIO()
            .execute { hotelDatabase.roomServiceDao().deleteRoomService(roomService) }
    }

    val cicoList: LiveData<List<CheckInCheckOutEntity>>
        get() = hotelDatabase.cicoDao().allCico

    fun getChosenCico(custId: Int): LiveData<CheckInCheckOutEntity> {
        return hotelDatabase.cicoDao().getChosenCico(custId)
    }

    fun getCicoByStatus(status: RoomStatus): LiveData<List<CheckInCheckOutEntity>> {
        return hotelDatabase.cicoDao().getCicoByStatus(status)
    }

    fun insertCico(cico: CheckInCheckOutEntity) {
        mExecutors.diskIO().execute { hotelDatabase.cicoDao().insertCico(cico) }
    }

    fun updateCico(cico: CheckInCheckOutEntity) {
        mExecutors.diskIO().execute { hotelDatabase.cicoDao().updateCico(cico) }
    }

    fun deleteCico(cico: CheckInCheckOutEntity) {
        mExecutors.diskIO().execute { hotelDatabase.cicoDao().deleteCico(cico) }
    }

    companion object {

        private var sInstance: HotelRepository? = null

        fun getInstance(database: HotelDatabase, executors: AppExecutors): HotelRepository {
            return sInstance ?: synchronized(this) {
                sInstance
                    ?: HotelRepository(
                        database,
                        executors
                    ).also { sInstance = it }
            }
        }
    }
}