/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.models.auth.verification_code

import android.os.Parcel
import android.os.Parcelable

/**
 * CodeDeliveryDetailsData
 *
 * This class is responsible to be verification code details data
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */

data class CodeDeliveryDetailsData(val AttributeName: String, val DeliveryMedium: String, val Destination: String) : Parcelable {

    //Create model with Parcel.
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    //To write to parcel. This is from Parcelable interface
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(AttributeName)
        parcel.writeString(DeliveryMedium)
        parcel.writeString(Destination)
    }

    //To describe bit. This is from Parcelable interface
    override fun describeContents(): Int {
        return 0
    }

    //To create parcelable object. This is from Parcelable interface
    companion object CREATOR : Parcelable.Creator<CodeDeliveryDetailsData> {
        //To create from Parcel
        override fun createFromParcel(parcel: Parcel): CodeDeliveryDetailsData {
            return CodeDeliveryDetailsData(parcel)
        }

        //Add new array
        override fun newArray(size: Int): Array<CodeDeliveryDetailsData?> {
            return arrayOfNulls(size)
        }
    }

}