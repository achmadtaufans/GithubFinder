/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.models.auth

import android.os.Parcel
import android.os.Parcelable

/**
 * AuthRequest
 *
 * This class is responsible to be request model which is related with register and Login
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */

data class AuthRequest(val Username: String?, val Password: String?, val UserType: String?, val MFA: Boolean?,
                       val VerificationCode: String?, var Provider: String?, var DeviceId: String?) : Parcelable {

    //Create AuthRequest model object with multiple parameters
    constructor(username: String, userType: String) : this(username, null, userType, null, null, null, null );

    constructor(username: String, password: String, userType: String, source: String, code: Int) :  this(username, password, userType, null, null, null, null) {
        when(code) {
            1 -> DeviceId = source;
            2 -> Provider = source;
        }
    }

    constructor(username: String, password: String, userType: String, _MFA: Boolean) : this(username, password, userType, _MFA, null, null, null)

    constructor(username: String, verificationCode: String, userType: String) : this(username, null, userType, null, verificationCode, null, null );

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    //Write AuthRequest model to Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Username)
        parcel.writeString(Password)
        parcel.writeString(UserType)
        parcel.writeValue(MFA)
        parcel.writeString(VerificationCode)
        parcel.writeString(Provider)
        parcel.writeString(DeviceId)
    }

    //A bitmask indicating the set of special object types marshaled by this Parcelable object instance.
    //Value is either 0 or CONTENTS_FILE_DESCRIPTOR. This is from Parcelable interface
    override fun describeContents(): Int {
        return 0
    }

    //To create parcelable object. This is from Parcelable interface
    companion object CREATOR : Parcelable.Creator<AuthRequest> {
        //To create from Parcel
        override fun createFromParcel(parcel: Parcel): AuthRequest {
            return AuthRequest(parcel)
        }
        //Add new array
        override fun newArray(size: Int): Array<AuthRequest?> {
            return arrayOfNulls(size)
        }
    }
}
