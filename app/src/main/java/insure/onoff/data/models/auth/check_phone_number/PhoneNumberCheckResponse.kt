/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.models.auth.check_phone_number

import android.os.Parcel
import android.os.Parcelable
import insure.onoff.data.models.auth.Error

/**
 * PhoneNumberCheckResponse
 *
 * This class is responsible to contain PhoneNumberCheckResponse data
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
data class PhoneNumberCheckResponse(val status: Boolean, val message: String, val data: List<PhoneNumberCheckData>, val error: List<Error>) : Parcelable {

    //Create model with Parcel.
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.createTypedArrayList(PhoneNumberCheckData),
        parcel.createTypedArrayList(Error)
    ) {
    }

    //To write to parcel. This is from Parcelable interface
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (status) 1 else 0)
        parcel.writeString(message)
        parcel.writeTypedList(data)
        parcel.writeTypedList(error)
    }

    /**
     * A bitmask indicating the set of special object types marshaled by this Parcelable object instance.
     * Value is either 0 or CONTENTS_FILE_DESCRIPTOR. This is from Parcelable interface
     */
    override fun describeContents(): Int {
        return 0
    }

    //To create parcelable object. This is from Parcelable interface
    companion object CREATOR : Parcelable.Creator<PhoneNumberCheckResponse> {
        //To create from Parcel
        override fun createFromParcel(parcel: Parcel): PhoneNumberCheckResponse {
            return PhoneNumberCheckResponse(parcel)
        }
        //Add new array
        override fun newArray(size: Int): Array<PhoneNumberCheckResponse?> {
            return arrayOfNulls(size)
        }
    }
}
