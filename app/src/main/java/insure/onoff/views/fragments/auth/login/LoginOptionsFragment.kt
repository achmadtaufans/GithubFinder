/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.views.fragments.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import insure.onoff.R
import insure.onoff.common.validation.FormFieldEmptyValidator
import insure.onoff.core.events.ShowToolbarEvent
import insure.onoff.core.events.UpdateToolbarNameEvent
import insure.onoff.databinding.FragmentLoginOptionsBinding
import org.greenrobot.eventbus.EventBus

/**
 * LoginOptionsFragment
 *
 * This class function is responsible to dispay login options
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
class LoginOptionsFragment : Fragment() {
    private lateinit var binding: FragmentLoginOptionsBinding

    // To display fragment and configure needed variables
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginOptionsBinding.inflate(inflater, container, false)

        EventBus.getDefault().post(UpdateToolbarNameEvent("Login"))
        EventBus.getDefault().post(ShowToolbarEvent(true))

        val bindingBtnLoginEmail = binding.btnLoginEmail

        // To send field and button to validating in FormFieldEmptyValidator class
        val editTextList = arrayOf<EditText>(binding.etMobileNumber)
        val textWatcher = FormFieldEmptyValidator(editTextList, binding.btnLoginPhone, "PhoneNumberValidation")

        for (editText in editTextList) {
            editText.addTextChangedListener(textWatcher)
        }

        bindingBtnLoginEmail.setOnClickListener { view ->
            view.findNavController().navigate(R.id.actionRegisterEmailFirstFragment)
        }

        bindingBtnLoginEmail.setOnClickListener { view ->
            view.findNavController().navigate(R.id.actionRegisterVerificationCode)
        }

        return binding.root
    }
}
