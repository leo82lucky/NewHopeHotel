package com.example.newhopehotel.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Register_users_table")
data class RegisterEntity(

    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "first_name")
    var firstName: String,

    @ColumnInfo(name = "last_name")
    var lastName: String,

    @ColumnInfo(name = "user_name")
    var userName: String,

    @ColumnInfo(name = "password_text")
    var passwrd: String,

    @ColumnInfo(name = "rooms_assigned")
    var roomsAssigned: Int

) : Parcelable {
    fun copy(): RegisterEntity {
        return RegisterEntity(
            userId, firstName, lastName, userName, passwrd, roomsAssigned
        )
    }
}
