/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.models.auth.check_phone_number

import android.os.Parcel
import android.os.Parcelable

/**
 * FederationValues
 *
 * This class is responsible to contain value from Federation
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
data class FederationValues(val _id: String) : Parcelable {

    //Create model with Parcel.
    constructor(parcel: Parcel) : this(parcel.readString()) {}

    //To write to parcel. This is from Parcelable interface
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
    }

    /**
     * A bitmask indicating the set of special object types marshaled by this Parcelable object instance.
     * Value is either 0 or CONTENTS_FILE_DESCRIPTOR. This is from Parcelable interface
     */
    override fun describeContents(): Int {
        return 0
    }

    //To create parcelable object. This is from Parcelable interface
    companion object CREATOR : Parcelable.Creator<FederationValues> {
        //To create from Parcel
        override fun createFromParcel(parcel: Parcel): FederationValues {
            return FederationValues(parcel)
        }
        //Add new array
        override fun newArray(size: Int): Array<FederationValues?> {
            return arrayOfNulls(size)
        }
    }
}
