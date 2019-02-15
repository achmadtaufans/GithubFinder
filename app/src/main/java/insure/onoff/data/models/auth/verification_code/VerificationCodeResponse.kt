/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.models.auth.verification_code

import android.os.Parcel
import android.os.Parcelable
import insure.onoff.data.models.auth.Error

/**
 * VerificationCodeResponse
 *
 * This class is responsible to be response which is related with VerificationCode
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
data class VerificationCodeResponse(val status: Boolean, val message: String, val data: VerificationCodeData, val error: List<Error>) : Parcelable {
    //Create model with Parcel.
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readParcelable(VerificationCodeData::class.java.classLoader),
        parcel.createTypedArrayList(Error)
    ) {
    }

    //To write to parcel. This is from Parcelable interface
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (status) 1 else 0)
        parcel.writeString(message)
        parcel.writeParcelable(data, flags)
        parcel.writeTypedList(error)
    }

    //A bitmask indicating the set of special object types marshaled by this Parcelable object instance.
    //Value is either 0 or CONTENTS_FILE_DESCRIPTOR. This is from Parcelable interface
    override fun describeContents(): Int {
        return 0
    }

    //To create parcelable object. This is from Parcelable interface
    companion object CREATOR : Parcelable.Creator<VerificationCodeResponse> {
        //To create from Parcel
        override fun createFromParcel(parcel: Parcel): VerificationCodeResponse {
            return VerificationCodeResponse(parcel)
        }

        //Add new array
        override fun newArray(size: Int): Array<VerificationCodeResponse?> {
            return arrayOfNulls(size)
        }
    }
}
