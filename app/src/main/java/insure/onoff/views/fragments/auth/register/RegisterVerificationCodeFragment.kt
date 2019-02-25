package insure.onoff.views.fragments.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import insure.onoff.R
import insure.onoff.core.events.UpdateToolbarNameEvent
import insure.onoff.databinding.FragmentRegisterVerificationCodeBinding
import org.greenrobot.eventbus.EventBus

/**
 * RegisterVerificationCodeFragment
 *
 * This class function is responsible to display Verification Code view when register by phone
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
class RegisterVerificationCodeFragment: Fragment() {
    private lateinit var binding: FragmentRegisterVerificationCodeBinding;

    /*
     * To display fragment and configurate needed variables
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterVerificationCodeBinding.inflate(inflater, container, false)

        EventBus.getDefault().post(UpdateToolbarNameEvent("Verification"))
        
        return binding.root
    }
}
