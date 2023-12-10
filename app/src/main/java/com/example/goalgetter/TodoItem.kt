package com.example.goalgetter

import android.os.Parcel
import java.time.LocalDate
import java.util.Date
import android.os.Parcelable
import kotlin.random.Random


data class TodoItem(
    val id: String,
    var task: String,
    var description: String,
    var date: LocalDate,
    var done: Boolean,
    var start: String,
    var end: String
) : Parcelable {
    // Implementing Parcelable methods
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        LocalDate.parse(parcel.readString()),
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(task)
        parcel.writeString(description)
        parcel.writeString(date.toString())
        parcel.writeByte(if (done) 1 else 0)
        parcel.writeString(start)
        parcel.writeString(end)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TodoItem> {
        override fun createFromParcel(parcel: Parcel): TodoItem {
            return TodoItem(parcel)
        }

        override fun newArray(size: Int): Array<TodoItem?> {
            return arrayOfNulls(size)
        }
    }
}
