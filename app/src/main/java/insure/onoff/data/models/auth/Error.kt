/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.models.auth

import android.os.Parcel
import android.os.Parcelable

data class Error(val code: String, val name: String, val message: String) : Parcelable {

    //Create model with Parcel.
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    //Write AuthRequest model to Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(code)
        parcel.writeString(name)
        parcel.writeString(message)
    }

    //To describe bit. This is from Parcelable interface
    override fun describeContents(): Int {
        return 0
    }

    //To create parcelable object. This is from Parcelable interface
    companion object CREATOR : Parcelable.Creator<Error> {
        //To create from Parcel
        override fun createFromParcel(parcel: Parcel): Error {
            return Error(parcel)
        }
        //Add new array
        override fun newArray(size: Int): Array<Error?> {
            return arrayOfNulls(size)
        }
    }

}