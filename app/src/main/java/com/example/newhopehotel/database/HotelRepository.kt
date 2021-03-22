package com.example.newhopehotel.database

import android.util.Log

class HotelRepository(
    private val registerDatabaseDao: RegisterDatabaseDao,
    private val checkInCheckOutDatabaseDao: CheckInCheckOutDatabaseDao
) {

    val users = registerDatabaseDao.getAllUsers()
    val customers = checkInCheckOutDatabaseDao.getAllCustomers()

    suspend fun insert(user: RegisterEntity) {
        return registerDatabaseDao.insert(user)
    }

    suspend fun getUserName(userName: String): RegisterEntity? {
        Log.i("MYTAG", "inside Repository GetUsers fun ")
        return registerDatabaseDao.getUsername(userName)
    }
    //suspend fun deleteAll(): Int {
    //    return dao.deleteAll()
    //}

    suspend fun insert(customer: CheckInCheckOutEntity) {
        return checkInCheckOutDatabaseDao.insert(customer)
    }

    suspend fun getCustomerName(customerName: String): CheckInCheckOutEntity? {
        Log.i("MYTAG", "hotel repo-getCustomerName() ")
        return checkInCheckOutDatabaseDao.getCustomerName(customerName)
    }
}