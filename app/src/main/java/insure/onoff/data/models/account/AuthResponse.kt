/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.models.account

import android.os.Parcel
import android.os.Parcelable

/**
 * AuthResponse
 *
 * This class is responsible to be as Auth model
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */

data class AuthResponse(val status: Boolean, val message: String, val data: Data, val error: Error) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readParcelable(Data::class.java.classLoader),
        parcel.readParcelable(Error::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (status) 1 else 0)
        parcel.writeString(message)
        parcel.writeParcelable(data, flags)
        parcel.writeParcelable(error, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AuthResponse> {
        override fun createFromParcel(parcel: Parcel): AuthResponse {
            return AuthResponse(parcel)
        }

        override fun newArray(size: Int): Array<AuthResponse?> {
            return arrayOfNulls(size)
        }
    }

}