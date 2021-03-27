package com.example.newhopehotel.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RegisterDatabaseDao {

    @Insert
    suspend fun insert(register: RegisterEntity)

    //@Delete
    //suspend  fun deleteSubscriber(register: RegisterEntity):Int

    @Query("SELECT * FROM Register_users_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<RegisterEntity>>

    @Query("DELETE FROM Register_users_table")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM Register_users_table WHERE user_name LIKE :userName")
    suspend fun getUsername(userName: String): RegisterEntity?

}

@Dao
interface CheckInCheckOutDatabaseDao {
    @Insert
    suspend fun insert(checkIn: CheckInCheckOutEntity)

    @Update
    suspend fun update(checkIn: CheckInCheckOutEntity)

    @Query("SELECT * FROM CheckIn_Checkout_Table ORDER BY custId DESC")
    fun getAllCustomers(): LiveData<List<CheckInCheckOutEntity>>

    @Query("DELETE FROM CheckIn_Checkout_Table")
    suspend fun deleteAll(): Int

//    @Delete
//    suspend fun deleteCustomer(checkIn: CheckInCheckOutEntity)

    @Query("SELECT * FROM CheckIn_Checkout_Table WHERE cust_name LIKE :customerName")
    suspend fun getCustomerName(customerName: String): CheckInCheckOutEntity?

    @Query("SELECT * FROM CheckIn_Checkout_Table ORDER BY custId DESC LIMIT 1")
    suspend fun getCustomer(): CheckInCheckOutEntity?


}