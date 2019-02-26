/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.models.auth.check_phone_number

import android.os.Parcel
import android.os.Parcelable
import java.math.BigInteger

/**
 * PhoneNumberCheckData
 *
 * This class is responsible to contain value from data param from PhoneNumberCheckResponse
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
data class PhoneNumberCheckData(val _id: String, val Method: List<PhoneNumberMethod>, val Id: String, val CreateTs: BigInteger,
                                val UpdateTs: BigInteger, val LastLoginTs: BigInteger, val Status: Int, val __v: Int) : Parcelable {

    //Create model with Parcel.
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        TODO("Method"),
        parcel.readString(),
        TODO("CreateTs"),
        TODO("UpdateTs"),
        TODO("LastLoginTs"),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    //To write to parcel. This is from Parcelable interface
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(Id)
        parcel.writeInt(Status)
        parcel.writeInt(__v)
    }

    /**
     * A bitmask indicating the set of special object types marshaled by this Parcelable object instance.
     * Value is either 0 or CONTENTS_FILE_DESCRIPTOR. This is from Parcelable interface
     */
    override fun describeContents(): Int {
        return 0
    }

    //To create parcelable object. This is from Parcelable interface
    companion object CREATOR : Parcelable.Creator<PhoneNumberCheckData> {
        //To create from Parcel
        override fun createFromParcel(parcel: Parcel): PhoneNumberCheckData {
            return PhoneNumberCheckData(parcel)
        }
        //Add new array
        override fun newArray(size: Int): Array<PhoneNumberCheckData?> {
            return arrayOfNulls(size)
        }
    }
}
