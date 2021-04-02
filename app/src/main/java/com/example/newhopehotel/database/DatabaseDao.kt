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