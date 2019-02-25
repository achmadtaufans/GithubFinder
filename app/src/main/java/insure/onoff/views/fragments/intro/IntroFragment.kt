/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.views.fragments.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager.widget.PagerAdapter
import insure.onoff.R
import insure.onoff.adapter.IllustrationAdapter
import insure.onoff.core.events.ShowToolbarEvent
import insure.onoff.databinding.FragmentIntroBinding
import kotlinx.android.synthetic.main.fragment_intro.*
import org.greenrobot.eventbus.EventBus

/**
 * IntroFragment
 *
 * This class function is responsible to display loreg introduction after splash screen finished
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
class IntroFragment : Fragment() {
    private lateinit var binding: FragmentIntroBinding;

    /*
     * list of CDN data
     */
    private val imageUrls = arrayOf(
        "il-en-lowcost.png",
        "il-en-easyclaim.png",
        "il-en-hourlyprotection.png"
    )

    /*
     * To display fragment and configurate needed variables
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        binding.tvRegister.setOnClickListener { view -> view.findNavController().navigate(R.id.actionRegisterOptionsFragment) }
        binding.btnLogin.setOnClickListener { view -> view.findNavController().navigate(R.id.actionLoginOptionsFragment) }

        EventBus.getDefault().post(ShowToolbarEvent(false))

        displayIllustrationsViewPager()

        return binding.root
    }

    /**
     *  To display illustrations on view pager
     */
    fun displayIllustrationsViewPager() {
        val adapter: PagerAdapter = IllustrationAdapter(context!!, imageUrls)

        binding.vpLoregIntro.adapter = adapter
        binding.tlIntro.setupWithViewPager(binding.vpLoregIntro, true)
    }
}
