package com.example.newhopehotel.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.newhopehotel.data.RoomID
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

    val feedbackList: LiveData<List<FeedbackEntity>>
        get() = hotelDatabase.feedbackListDao().allFeedback
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

    val cleaningList: LiveData<List<CleaningListEntity>>
        get() = hotelDatabase.cleaningListDao().allCleaningList

    fun insertCleaningList(cleaningList: CleaningListEntity) {
        mExecutors.diskIO().execute { hotelDatabase.cleaningListDao().insertCleaningList(cleaningList) }
    }

    fun updateCleaningList(cleaningList: CleaningListEntity) {
        mExecutors.diskIO().execute { hotelDatabase.cleaningListDao().updateCleaningList(cleaningList) }
    }

    fun deleteCleaningList(cleaningList: CleaningListEntity) {
        mExecutors.diskIO().execute { hotelDatabase.cleaningListDao().deleteCleaningList(cleaningList) }
    }

    fun getCleaningListByUserID(userID: Int): LiveData<List<CleaningListEntity>> {
        return hotelDatabase.cleaningListDao().findCleaningList(userID)
    }

    val roomToCleanList: LiveData<List<RoomToCleanEntity>>
        get() = hotelDatabase.roomToCleanDao().allRoomToClean

    fun insertRoomToClean(roomToClean: RoomToCleanEntity) {
        mExecutors.diskIO().execute { hotelDatabase.roomToCleanDao().insertRoomToClean(roomToClean) }
    }

    fun deleteRoomToClean(roomToClean: RoomToCleanEntity) {
        mExecutors.diskIO().execute { hotelDatabase.roomToCleanDao().deleteRoomToClean(roomToClean) }
    }

    fun deleteAllRoomToClean() {
        mExecutors.diskIO().execute { hotelDatabase.roomToCleanDao().deleteAllRoomToClean() }
    }

    fun changeRoomToCleanBorderColor(roomID: RoomID, new: Int) {
        mExecutors.diskIO().execute { hotelDatabase.roomToCleanDao().changeRoomToCleanBorderColor(roomID, new) }
    }

    fun updateRoomToClean(roomToClean: RoomToCleanEntity) {
        mExecutors.diskIO().execute { hotelDatabase.roomToCleanDao().updateRoomToClean(roomToClean) }
    }

    fun getRoomToCleanByTime(cleaningTime: String): LiveData<List<RoomToCleanEntity>> {
        return hotelDatabase.roomToCleanDao().getRoomToCleanByTime(cleaningTime)
    }

    val selectedWorkerList: List<SelectedWorkerEntity>
        get() = hotelDatabase.selectedWorkerDao().allSelectedWorker

    fun insertSelectedWorker(selectedWorker: SelectedWorkerEntity) {
        mExecutors.diskIO().execute { hotelDatabase.selectedWorkerDao().insertSelectedWorker(selectedWorker) }
    }

    fun deleteAllSelectedWorker() {
        mExecutors.diskIO().execute { hotelDatabase.selectedWorkerDao().deleteAllSelectedWorker() }
    }

    fun getFeedbackListByUserID(feedbackID: Int): LiveData<List<FeedbackEntity>>{
        return hotelDatabase.feedbackListDao().getChosenFeedback(feedbackID)
    }

    fun insertFeedbackList(feedbackList: FeedbackEntity) {
        mExecutors.diskIO().execute { hotelDatabase.feedbackListDao().insertFeedback(feedbackList) }
    }

    fun deleteFeedbackList(feedbackList: FeedbackEntity) {
        mExecutors.diskIO().execute { hotelDatabase.feedbackListDao().deleteFeedback(feedbackList) }
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