/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.models.sample

import android.os.Parcel
import android.os.Parcelable

/**
 * Request
 *
 * This class is responsible to be request model
 *
 * Note : This class is temporary to understand Jetpack architecture as simple as possible
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */

data class Request(val name: String?, val job: String?) : Parcelable {

    //Create model with Parcel.
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    //To write to parcel. This is from Parcelable interface
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(job)
    }

    //To describe bit. This is from Parcelable interface
    override fun describeContents(): Int {
        return 0
    }

    //To create parcelable object. This is from Parcelable interface
    companion object CREATOR : Parcelable.Creator<Request> {
        //To create from Parcel
        override fun createFromParcel(parcel: Parcel): Request {
            return Request(parcel)
        }

        //Add new array
        override fun newArray(size: Int): Array<Request?> {
            return arrayOfNulls(size)
        }
    }

}