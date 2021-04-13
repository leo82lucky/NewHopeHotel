package com.example.newhopehotel.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RegisterDatabaseDao {

    @Insert
    fun insert(register: RegisterEntity)

    //@Delete
    //suspend  fun deleteSubscriber(register: RegisterEntity):Int

    //    @Query("SELECT * FROM Register_users_table ORDER BY userId DESC")
//    fun getAllUsers(): LiveData<List<RegisterEntity>>
    @get:Query("SELECT * FROM Register_users_table")
    val allUsers: LiveData<List<RegisterEntity>>

    @Query("DELETE FROM Register_users_table")
    fun deleteAll(): Int

    @Query("SELECT * FROM Register_users_table WHERE user_name LIKE :userName")
    suspend fun getUsername(userName: String): RegisterEntity?

}

@Dao
interface CheckInCheckOutDatabaseDao {
    @Insert
    fun insertCico(checkIn: CheckInCheckOutEntity)

    @get:Query("SELECT * FROM CheckIn_Checkout_Table")
    val allCico: LiveData<List<CheckInCheckOutEntity>>

    @Query("SELECT * FROM CheckIn_Checkout_Table WHERE cicoId = :id")
    fun getChosenCico(id: Int): LiveData<CheckInCheckOutEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCico(checkIn: CheckInCheckOutEntity)

//    @Query("SELECT * FROM CheckIn_Checkout_Table ORDER BY custId DESC")
//    fun getAllCustomers(): LiveData<List<CheckInCheckOutEntity>>

    @Query("DELETE FROM CheckIn_Checkout_Table")
    fun deleteAllCico(): Int

    @Delete
    fun deleteCico(checkIn: CheckInCheckOutEntity)

    @Query("SELECT * FROM CheckIn_Checkout_Table WHERE custName LIKE :customerName")
    fun getCustomerName(customerName: String): CheckInCheckOutEntity?

//    @Query("SELECT * FROM CheckIn_Checkout_Table ORDER BY custId DESC LIMIT 1")
//    suspend fun getCustomer(): CheckInCheckOutEntity?
}

@Dao
interface MorningCallDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMorningCall(morningCall: MorningCallEntity)

    @get:Query("SELECT * FROM Morning_Call_Table")
    val allMorningCall: LiveData<List< MorningCallEntity>>

    @Query("SELECT * FROM Morning_Call_Table WHERE mcId = :id")
    fun getChosenMorningCall(id: Int): LiveData<MorningCallEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMorningCall(morningCall: MorningCallEntity)

//    @Query("SELECT * FROM CheckIn_Checkout_Table ORDER BY custId DESC")
//    fun getAllCustomers(): LiveData<List<CheckInCheckOutEntity>>

    @Query("DELETE FROM Morning_Call_Table")
    fun deleteAllMorningCall(): Int

    @Delete
    fun deleteMorningCall(checkIn: MorningCallEntity)

    @Query("SELECT * FROM Morning_Call_Table WHERE custName LIKE :customerName")
    fun getCustomerName(customerName: String): MorningCallEntity?

//    @Query("SELECT * FROM CheckIn_Checkout_Table ORDER BY custId DESC LIMIT 1")
//    suspend fun getCustomer(): CheckInCheckOutEntity?
}


@Dao
interface RoomServiceDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoomService(roomService: RoomServiceEntity)

    @get:Query("SELECT * FROM Room_Service_Table")
    val allRoomService: LiveData<List< RoomServiceEntity>>

    @Query("SELECT * FROM room_service_table WHERE rsId = :id")
    fun getChosenRoomService(id: Int): LiveData<RoomServiceEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateRoomService(roomService: RoomServiceEntity)

//    @Query("SELECT * FROM CheckIn_Checkout_Table ORDER BY custId DESC")
//    fun getAllCustomers(): LiveData<List<CheckInCheckOutEntity>>

    @Query("DELETE FROM Room_Service_Table")
    fun deleteAllRoomService(): Int

    @Delete
    fun deleteRoomService(checkIn: RoomServiceEntity)

    @Query("SELECT * FROM Room_Service_Table WHERE custName LIKE :customerName")
    fun getCustomerName(customerName: String): RoomServiceEntity?

//    @Query("SELECT * FROM CheckIn_Checkout_Table ORDER BY custId DESC LIMIT 1")
//    suspend fun getCustomer(): CheckInCheckOutEntity?
}

@Dao
interface FeedbackDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeedback(feedback: FeedbackEntity)

    @get:Query("SELECT * FROM Feedback_Table")
    val allFeedback: LiveData<List< FeedbackEntity>>

    @Query("SELECT * FROM Feedback_Table WHERE feedbackID = :id")
    fun getChosenFeedback(id: Int): LiveData<FeedbackEntity>

    @Query("DELETE FROM Feedback_Table")
    fun deleteAllFeedback(): Int

    @Delete
    fun deleteFeedback(feedback: FeedbackEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFeedback(feedback: FeedbackEntity)

}