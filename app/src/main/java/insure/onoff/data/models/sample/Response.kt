/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.data.models.sample

import android.os.Parcel
import android.os.Parcelable

/**
 * Response
 *
 * This class is responsible to be response model
 *
 * Note : This class is temporary to understand Jetpack architecture as simple as possible
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */

data class Response(val name: String?, val job: String?, val id: Int?, val createdAt: String?) : Parcelable {

    //Create model with Parcel.
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    //To write to parcel. This is from Parcelable interface
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(job)
        parcel.writeValue(id)
        parcel.writeString(createdAt)
    }

    //To describe bit. This is from Parcelable interface
    override fun describeContents(): Int {
        return 0
    }

    //To create from Parcel. This is from Parcelable interface
    companion object CREATOR : Parcelable.Creator<Response> {

        //To create from Parcel
        override fun createFromParcel(parcel: Parcel): Response {
            return Response(parcel)
        }

        //Add new array
        override fun newArray(size: Int): Array<Response?> {
            return arrayOfNulls(size)
        }
    }

}