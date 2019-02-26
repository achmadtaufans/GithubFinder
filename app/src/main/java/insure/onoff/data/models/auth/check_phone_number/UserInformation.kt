/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.models.auth.check_phone_number

import android.os.Parcel
import android.os.Parcelable

/**
 * UserInformation
 *
 * This class is responsible to contain user information
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
data class UserInformation(val Federation: List<FederationValues>, val _id: String, val MobileNumber: String, val PwdHash: String, val PwdSalt: String) : Parcelable {
    //Create model with Parcel.
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(FederationValues),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    //To write to parcel. This is from Parcelable interface
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(Federation)
        parcel.writeString(_id)
        parcel.writeString(MobileNumber)
        parcel.writeString(PwdHash)
        parcel.writeString(PwdSalt)
    }

    /**
     * A bitmask indicating the set of special object types marshaled by this Parcelable object instance.
     * Value is either 0 or CONTENTS_FILE_DESCRIPTOR. This is from Parcelable interface
     */
    override fun describeContents(): Int {
        return 0
    }

    //To create parcelable object. This is from Parcelable interface
    companion object CREATOR : Parcelable.Creator<UserInformation> {
        //To create from Parcel
        override fun createFromParcel(parcel: Parcel): UserInformation {
            return UserInformation(parcel)
        }

        //Add new array
        override fun newArray(size: Int): Array<UserInformation?> {
            return arrayOfNulls(size)
        }
    }
}
