package com.example.newhopehotel.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "Morning_Call_Table")
data class MorningCallEntity(

    var custName: String,
    var time: String,
    var date: String,
    @PrimaryKey(autoGenerate = true) val mcId: Int = 0
) : Parcelable {
    fun copy(): MorningCallEntity {
        return MorningCallEntity(
            custName, time,date,
            mcId
        )
    }
}