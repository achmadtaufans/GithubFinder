/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.utilities

import android.text.InputType
import android.util.Log
import android.widget.EditText
import androidx.databinding.BindingAdapter

/**
 * BindingAdaptersManager
 *
 * This file contains binding adapters from XML to Kotlin. Binding adapter makes user can give value from XML to value based on custom keyword.
 * Then in this file, you call the keyword with @BindingAdapter()
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */

//Getting value from app:editTextType of editText in XML
@BindingAdapter("editTextType")
fun setEditTextType(editText: EditText, type: String) {
    when(type) {
        "email" -> { editText.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) }
        "password" -> { editText.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) }
    }
}
