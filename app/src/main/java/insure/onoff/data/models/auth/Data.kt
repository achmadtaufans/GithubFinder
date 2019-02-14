/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.models.auth

import android.os.Parcel
import android.os.Parcelable

data class Data(val Username: String, val UserType: String, val UserToken: String) : Parcelable {
    //Create model with Parcel.
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    //Write AuthRequest model to Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Username)
        parcel.writeString(UserType)
        parcel.writeString(UserToken)
    }

    //To describe bit. This is from Parcelable interface
    override fun describeContents(): Int {
        return 0
    }

    //To create parcelable object. This is from Parcelable interface
    companion object CREATOR : Parcelable.Creator<Data> {
        //To create from Parcel
        override fun createFromParcel(parcel: Parcel): Data {
            return Data(parcel)
        }
        //Add new array
        override fun newArray(size: Int): Array<Data?> {
            return arrayOfNulls(size)
        }
    }

}