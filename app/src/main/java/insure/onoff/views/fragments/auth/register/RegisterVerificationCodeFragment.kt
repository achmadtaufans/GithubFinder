package insure.onoff.views.fragments.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import insure.onoff.R
import insure.onoff.core.events.UpdateToolbarNameEvent
import insure.onoff.databinding.FragmentRegisterVerificationCodeBinding
import insure.onoff.utilities.InjectorUtils
import insure.onoff.viewmodels.AuthViewModel
import org.greenrobot.eventbus.EventBus
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.navigation.Navigation.findNavController

/**
 * RegisterVerificationCodeFragment
 *
 * This class function is responsible to display Verification Code view when register by phone
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
class RegisterVerificationCodeFragment: Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentRegisterVerificationCodeBinding;
    private lateinit var viewModel: AuthViewModel;
    private lateinit var phoneNumber: String;

    /*
    * To display fragment and configurate needed variables
    */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterVerificationCodeBinding.inflate(inflater, container, false)
        EventBus.getDefault().post(UpdateToolbarNameEvent("Verification"))

        val factory = InjectorUtils.provideAuthViewModelFactory(context!!)
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java);

        binding.tvResend.setOnClickListener(this)
        binding.btnVerify.setOnClickListener(this)
        binding.tvAnotherVerification.setOnClickListener(this)
        binding.etVerificationCode.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
                if (binding.etVerificationCode.text.length == 6) {
                    binding.btnVerify.isClickable = true;
                    binding.btnVerify.setBackgroundResource(R.drawable.shape_rounded_rectangle_yellow)
                } else {
                    binding.btnVerify.isClickable = false;
                    binding.btnVerify.setBackgroundResource(R.drawable.shape_rounded_rectangle_gray)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { }

            override fun afterTextChanged(s: Editable) { }
        })

        phoneNumber = RegisterVerificationCodeFragmentArgs.fromBundle(arguments!!).phoneNumber
        registerByPhone(phoneNumber)

        return binding.root;
    }

    //Register by phone with calling View Model
    fun registerByPhone(phoneNumber: String) {
        viewModel.registerByPhone(phoneNumber, "", "customer", true).observe(viewLifecycleOwner,
            Observer {
                    responseData ->
                if (responseData != null) {

                }
            }
        )
    }

    //Maintain every event after clicking element
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvResend -> {
                viewModel.resendConfirmation(phoneNumber, "customer").observe(viewLifecycleOwner, Observer { responseData ->
                    if (responseData != null) {
                        //TODO("If success, it doesn't do anything on view. But if fail, it sends message that server fails to send resent code")
                    }
                })
            }
            R.id.btnVerify -> {
                viewModel.confirmOTP(phoneNumber, binding.etVerificationCode.text.toString(), "customer").observe(viewLifecycleOwner, Observer {
                    //TODO("If success, Go to dashboard success. If failed, still in page")
                })
            }
            R.id.tvAnotherVerification -> {
                v?.findNavController().navigate(R.id.actionRegisterOtherVersMethodsFragment)
            }
        }
    }
}
