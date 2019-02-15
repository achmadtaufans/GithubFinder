/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.models.auth.verification_code

import android.os.Parcel
import android.os.Parcelable
/**
 * VerificationCodeData
 *
 * This class is responsible to contain CodeDeliveryDetails data
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
data class VerificationCodeData(val CodeDeliveryDetails : CodeDeliveryDetailsData?) : Parcelable {
    //Create model with Parcel.
    constructor(parcel: Parcel) : this(parcel.readParcelable<CodeDeliveryDetailsData>(CodeDeliveryDetailsData::class.java.classLoader)) { }

    //To write to parcel. This is from Parcelable interface
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(CodeDeliveryDetails, flags)
    }

    //A bitmask indicating the set of special object types marshaled by this Parcelable object instance.
    //Value is either 0 or CONTENTS_FILE_DESCRIPTOR. This is from Parcelable interface
    override fun describeContents(): Int {
        return 0
    }

    //To create parcelable object. This is from Parcelable interface
    companion object CREATOR : Parcelable.Creator<VerificationCodeData> {
        //To create from Parcel
        override fun createFromParcel(parcel: Parcel): VerificationCodeData {
            return VerificationCodeData(parcel)
        }

        //Add new array
        override fun newArray(size: Int): Array<VerificationCodeData?> {
            return arrayOfNulls(size)
        }
    }
}
