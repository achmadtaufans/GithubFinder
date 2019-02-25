/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.views.fragments.loreg

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager.widget.PagerAdapter
import insure.onoff.R
import insure.onoff.adapter.IllustrationAdapter
import insure.onoff.core.events.ShowToolbarEvent
import insure.onoff.databinding.FragmentLoregBinding
import insure.onoff.utilities.SLIDER_DURATION
import insure.onoff.utilities.SLIDER_PERIOD
import kotlinx.android.synthetic.main.fragment_loreg.*
import org.greenrobot.eventbus.EventBus
import java.util.*

/**
 * LoregFragment
 *
 * This class function is responsible to display loreg introduction after splash screen finished
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
class LoregFragment : Fragment() {
    private lateinit var binding: FragmentLoregBinding

    /*
     * list of CDN data
     */
    private val imageUrls = arrayOf(
        "il-en-lowcost.png",
        "il-en-easyclaim.png",
        "il-en-hourlyprotection.png"
    )

    // Value for handle page of auto slide
    private var currentPage = 0
    private var numPages = 0

    /*
     * To display fragment and configurate needed variables
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoregBinding.inflate(inflater, container, false)
        binding.tvRegister.setOnClickListener { view ->
            view.findNavController().navigate(R.id.actionRegisterOptionsFragment)
        }
        binding.btnLogin.setOnClickListener { view ->
            view.findNavController().navigate(R.id.actionLoginOptionsFragment)
        }

        EventBus.getDefault().post(ShowToolbarEvent(false))

        displayIllustrationsViewPager()

        return binding.root
    }

    /**
     *  To display illustrations on view pager
     */
    private fun displayIllustrationsViewPager() {
        val adapter: PagerAdapter = IllustrationAdapter(context!!, imageUrls)

        binding.vpLoregIntro.adapter = adapter
        binding.tlIntro.setupWithViewPager(binding.vpLoregIntro, true)
    }

    /**
     *  To run function after view created
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter: PagerAdapter = IllustrationAdapter(context!!, imageUrls)
        vpLoregIntro.adapter = adapter

        // set slider indicator from illustration viewpager
        tlIntro.setupWithViewPager(vpLoregIntro, true)

        // set auto slide illustration
        numPages = imageUrls.size
        val handler = Handler()
        val update = Runnable {
            if (currentPage == numPages) {
                currentPage = 0
            }
            vpLoregIntro.setCurrentItem(currentPage++, true)
        }

        // set timer for auto slide illustration
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, SLIDER_DURATION, SLIDER_PERIOD)
    }
}
