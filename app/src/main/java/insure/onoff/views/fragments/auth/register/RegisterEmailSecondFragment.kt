/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.views.fragments.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import insure.onoff.common.validation.FormFieldEmptyValidator
import insure.onoff.databinding.FragmentRegisterEmailPasswordBinding

/**
 * RegisterEmailSecondFragment
 *
 * This class function is responsible to display register by email in second view
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
class RegisterEmailSecondFragment : Fragment() {
    private lateinit var binding: FragmentRegisterEmailPasswordBinding;

    /*
     * To display fragment and configurate needed variables
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterEmailPasswordBinding.inflate(inflater, container, false)

        val editTextList = arrayOf<EditText>(
            binding.layoutEmailEditText.editText,
            binding.layoutPasswordEditText.editText,
            binding.layoutVerifPasswordEditText.editText
        )

        val textWatcher = FormFieldEmptyValidator(editTextList, binding.btnRegister,"FieldValidation")

        for (editText in editTextList) {
            editText.addTextChangedListener(textWatcher)
        }

        binding.layoutEmailEditText.editText.setText(RegisterEmailSecondFragmentArgs.fromBundle(arguments!!).email)

        return binding.root
    }
}

