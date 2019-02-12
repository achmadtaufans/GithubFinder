/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.models.account

import android.os.Parcel
import android.os.Parcelable

data class Error(val code: String, val name: String, val message: String) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(code)
        parcel.writeString(name)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Error> {
        override fun createFromParcel(parcel: Parcel): Error {
            return Error(parcel)
        }

        override fun newArray(size: Int): Array<Error?> {
            return arrayOfNulls(size)
        }
    }

}