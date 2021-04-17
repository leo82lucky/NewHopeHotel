package com.example.newhopehotel.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Feedback_Table")
data class FeedbackEntity(

    @PrimaryKey(autoGenerate = true)
    var feedbackId: Int = 0,

    @ColumnInfo(name = "user_name")
    var username: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "question")
    var question: String,

    @ColumnInfo(name = "answer")
    var answer: String


): Parcelable {
    fun copy(): FeedbackEntity {
        return FeedbackEntity(
            feedbackId, username, date, question, answer
        )
    }
}