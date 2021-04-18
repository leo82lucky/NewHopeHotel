package com.example.newhopehotel.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.newhopehotel.data.RoomID
import com.example.newhopehotel.data.RoomStatus
import com.example.newhopehotel.utils.AppExecutors

class HotelRepository private constructor(
    private val hotelDatabase: HotelDatabase,
    private val mExecutors: AppExecutors
) {

    val users: LiveData<List<RegisterEntity>>
        get() = hotelDatabase.registerDao().allUsers

    val feedbackList: LiveData<List<FeedbackEntity>>
        get() = hotelDatabase.feedbackListDao().allFeedback

    fun insert(user: RegisterEntity) {
        mExecutors.diskIO().execute { hotelDatabase.registerDao().insert(user) }
    }

    suspend fun getUserName(userName: String): RegisterEntity? {
        return hotelDatabase.registerDao().getUsername(userName)
    }


    fun updateRoomsAssignedByUserID(userID: Int, roomsAssigned: Int) {
        mExecutors.diskIO().execute { hotelDatabase.registerDao().updateRoomsAssignedByUserID(userID, roomsAssigned) }
    }

    val roomServiceList: LiveData<List<RoomServiceEntity>>
        get() = hotelDatabase.roomServiceDao().allRoomService

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

    fun deleteRoomToClean(roomID: RoomID) {
        mExecutors.diskIO().execute { hotelDatabase.roomToCleanDao().deleteRoomToClean(roomID) }
    }

    fun deleteAllRoomToClean() {
        mExecutors.diskIO().execute { hotelDatabase.roomToCleanDao().deleteAllRoomToClean() }
    }

    fun changeRoomToCleanBorderColor(roomID: RoomID, new: Int) {
        mExecutors.diskIO().execute { hotelDatabase.roomToCleanDao().changeRoomToCleanBorderColor(roomID, new) }
    }

    fun getFeedbackListByUserID(feedbackID: Int): LiveData<FeedbackEntity>{
        return hotelDatabase.feedbackListDao().getChosenFeedback(feedbackID)
    }

    fun insertFeedbackList(feedbackList: FeedbackEntity) {
        mExecutors.diskIO().execute { hotelDatabase.feedbackListDao().insertFeedback(feedbackList) }
    }

    fun deleteFeedbackList(id: Int) {
        mExecutors.diskIO().execute { hotelDatabase.feedbackListDao().deleteFeedback(id) }
    }

    fun updateFeedbackList(feedbackList: FeedbackEntity) {
        mExecutors.diskIO().execute { hotelDatabase.feedbackListDao().updateFeedback(feedbackList) }
    }

    fun getSelectedFeedbacks(str: String): LiveData<List<FeedbackEntity>>{
        return hotelDatabase.feedbackListDao().getSelectedFeedbacks(str)
    }

    fun getSelectedViewedFeedbacks(str: String): LiveData<List<FeedbackEntity>>{
        return hotelDatabase.feedbackListDao().getSelectedViewedFeedbacks(str)
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