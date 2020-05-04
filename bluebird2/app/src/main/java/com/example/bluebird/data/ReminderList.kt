package com.example.bluebird.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * model from Endpoint to get data and save to database
 */
@Entity(tableName = "my_reminder")
data class ReminderList(
    @PrimaryKey(autoGenerate = true) var id: Long?
) : Parcelable {
    @ColumnInfo(name = "deviceID") var deviceID: Long? = 0L
    @ColumnInfo(name = "title") var title: String? = ""
    @ColumnInfo(name = "description")  var description: String? = ""
    @ColumnInfo(name = "date_time")  var dateTime: String? = ""

    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long
    ) {
        title = parcel.readString()
        description = parcel.readString()
        dateTime = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id!!)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(dateTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReminderList> {
        override fun createFromParcel(parcel: Parcel): ReminderList {
            return ReminderList(parcel)
        }

        override fun newArray(size: Int): Array<ReminderList?> {
            return arrayOfNulls(size)
        }
    }
}