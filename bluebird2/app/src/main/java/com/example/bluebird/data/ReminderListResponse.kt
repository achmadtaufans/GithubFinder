/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package com.example.bluebird.data

import android.os.Parcel
import android.os.Parcelable

/**
 * set response model
 */
data class ReminderListResponse(
    var todos: List<ReminderList>?
): Parcelable {

    /**
     * Create model with Parcel
     */
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(ReminderList)
    ) {
    }

    /**
     * Write WalletRequest model to Parcel
     */
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(todos)
    }

    /**
     * A bitmask indicating the set of special object types marshaled by this Parcelable object instance.
     * Value is either 0 or CONTENTS_FILE_DESCRIPTOR. This is from Parcelable interface
     */
    override fun describeContents(): Int {
        return 0
    }

    /**
     * To create parcelable object. This is from Parcelable interface
     */
    companion object CREATOR : Parcelable.Creator<ReminderListResponse> {
        /**
         *   To create from Parcel
         */
        override fun createFromParcel(parcel: Parcel): ReminderListResponse {
            return ReminderListResponse(parcel)
        }

        /**
         * Add new array
         */
        override fun newArray(size: Int): Array<ReminderListResponse?> {
            return arrayOfNulls(size)
        }
    }
}
