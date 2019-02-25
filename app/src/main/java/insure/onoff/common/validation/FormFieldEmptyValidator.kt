/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.common.validation

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText

/**
 * FormFieldEmptyValidator
 *
 * This class is responsible to be text watcher of edittexts
 *
 * @author    Taufan Setiawan  <taufansetiawan@onoff.insure>
 */


class FormFieldEmptyValidator(internal var editTextList: Array<EditText>, button: Button, var typeValidation: String) : TextWatcher {
    internal var view: View
    
    lateinit var mobilNumberWithout0 : String
    var enableButton : Boolean = false
    
    init {
        this.view = button
    }
    
    // checking input field every character before typing some character
    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {}
    
    // checking input field every character when typing some character
    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {}
    
    // checking input field every character after typing some character
    override fun afterTextChanged(editable: Editable) {
        // checking editText in editTextListFunction
        for (editText in editTextList) {
                // Checking type validation of PhoneNumberValidation to enable button and remove first "0"
                if (typeValidation == "PhoneNumberValidation") {
                    if (editText.text.toString().length == 2 && editText.text[0] == '0') {
                        mobilNumberWithout0 = editText.text.toString().replace("0".toRegex(), "")
                        editText.removeTextChangedListener(this)
                        editText.setText(mobilNumberWithout0)
                        editText.setSelection(mobilNumberWithout0.length)
                        editText.addTextChangedListener(this)
                    }
                    
                    // toggle the button and changes transparency
                    enableButton = editText.text.toString().length > 8

                    view.isEnabled = enableButton
                    view.alpha = if (enableButton) 1f else .5f
                } else if (typeValidation == "FieldValidation") {
                    // Checking type validation of FieldValidation to enable button if field is not null
                    enableButton = editText.text.length > 0

                    view.isEnabled = enableButton
                    view.alpha = if (enableButton) 1f else .5f
                }
        }
    }
}

