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
import androidx.navigation.findNavController
import insure.onoff.common.validation.FormFieldEmptyValidator
import insure.onoff.core.events.UpdateToolbarNameEvent
import insure.onoff.databinding.FragmentRegisterEmailBinding
import org.greenrobot.eventbus.EventBus

/**
 * RegisterEmailFirstFragment
 *
 * This class function is responsible to display register by email in first view
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
class RegisterEmailFirstFragment : Fragment() {
    private lateinit var binding: FragmentRegisterEmailBinding;

    /*
     * To display fragment and configurate needed variables
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterEmailBinding.inflate(inflater, container, false)
        EventBus.getDefault().post(UpdateToolbarNameEvent("Register"))

        val editTextList = arrayOf<EditText>(binding.layoutEmailEditText.editText)
        val textWatcher = FormFieldEmptyValidator(editTextList, binding.btnRegister, "FieldValidation")

        for (editText in editTextList) {
            editText.addTextChangedListener(textWatcher)
        }

        binding.btnRegister.setOnClickListener {
            view ->
                val email : String = binding.layoutEmailEditText.editText.text.toString()
                val direction = RegisterEmailFirstFragmentDirections.actionRegisterEmailSecondFragment(email)

                view.findNavController().navigate(direction)
        }

        return binding.root
    }
}
