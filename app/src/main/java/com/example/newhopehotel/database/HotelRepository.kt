package com.example.newhopehotel.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.newhopehotel.utils.AppExecutors

class HotelRepository private constructor(
//    private val registerDatabaseDao: RegisterDatabaseDao,
//    private val checkInCheckOutDatabaseDao: CheckInCheckOutDatabaseDao,
    private val hotelDatabase: HotelDatabase,
    private val mExecutors: AppExecutors
) {
    val users: LiveData<List<RegisterEntity>>
        get() = hotelDatabase.registerDao().allUsers

    val customers: LiveData<List<CheckInCheckOutEntity>>
        get() = hotelDatabase.cicoDao().allCico

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
    //suspend fun deleteAll(): Int {
    //    return dao.deleteAll()
    //}

//    suspend fun insertCustomer(customer: CheckInCheckOutEntity) {
//        return checkInCheckOutDatabaseDao.insert(customer)
//    }
//
//    suspend fun getCustomerName(customerName: String): CheckInCheckOutEntity? {
//        Log.i("MYTAG", "hotel repo-getCustomerName() ")
//        return checkInCheckOutDatabaseDao.getCustomerName(customerName)
//    }
//
//    suspend fun getCustomer(custid:Int): CheckInCheckOutEntity? {
//        Log.i("MYTAG", "hotel repo-getCustomer() ")
//        return checkInCheckOutDatabaseDao.getChosenCustomer(custid)
//    }

//    suspend fun deleteCustomer(customer: CheckInCheckOutEntity) {
//        Log.i("MYTAG", "hotel repo-deleteCustomer() ")
//        return checkInCheckOutDatabaseDao.deleteCustomer(customer)
//    }
//
//    suspend fun deleteAllCustomer(): Int {
//        return customers.deleteAll()
//    }

    val cicoList: LiveData<List<CheckInCheckOutEntity>>
        get() = hotelDatabase.cicoDao().allCico

    fun getChosenCico(custId: Int): LiveData<CheckInCheckOutEntity> {
        return hotelDatabase.cicoDao().getChosenCico(custId)
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