/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.views.fragments.loreg

import android.content.Intent
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
import insure.onoff.utilities.CDN_LOREG
import insure.onoff.utilities.SLIDER_DURATION
import insure.onoff.utilities.SLIDER_START_DELAY
import insure.onoff.views.activities.LoregActivity
import kotlinx.android.synthetic.main.fragment_loreg.*
import org.greenrobot.eventbus.EventBus
import java.util.*

/**
 * LoregFragment
 *
 * This class responsible to create page for loreg first view
 *
 * @author    Charles S  <charlessetiadi@onoff.insure>
 */
class LoregFragment : Fragment() {
    private lateinit var binding: FragmentLoregBinding

    // List of CDN link
    private var imageFileName: Array<String>? = null

    // Set first value for page of auto slide
    private var pagePosition = 0
    private var arraySize = 0

    // Set Handler for change illustration page
    private var handler = Handler()
    private var slide: Runnable? = null

    // Set timer for auto slide illustration
    private var swipeTimer = Timer()

    /**
     *  create view by inflating layout using navigation via binding
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

        return binding.root
    }

    /**
     *  To run function after view created
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // get CDN link from XML String
        imageFileName = context!!.resources.getStringArray(R.array.cdn)

        // lock switch icon position
        switchLanguages.isChecked = (Locale.getDefault().toLanguageTag().toString() == "id")

        // Set trigger for auto slide illustration
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(slide)
            }
        }, SLIDER_START_DELAY, SLIDER_DURATION)

        // Run illustration when first view created
        runSlider()

        switchLanguages.setOnCheckedChangeListener { buttonView, isChecked ->
            setLocale(if (isChecked) "id" else "en")
        }
    }

    /**
     *  Create slider view
     */
    private fun runSlider() {
        val adapter: PagerAdapter = IllustrationAdapter(context!!, imageFileName!!, CDN_LOREG)

        // Set content into viewpager(vp)
        binding.vpLoregIntro.adapter = adapter

        // create tablayout(tl) for make dot indicator below the viewpager
        binding.tlIntro.setupWithViewPager(binding.vpLoregIntro, true)

        // Change page every trigger by timer
        arraySize = imageFileName!!.size
        slide = Runnable {
            if (pagePosition == arraySize) {
                pagePosition = 0
            }
            binding.vpLoregIntro.setCurrentItem(pagePosition++, true)
        }
    }

    /**
     *  switch locale string to change language (restart)
     */
    private fun setLocale(lang: String) {
        val res = resources
        val conf = res.configuration
        conf.setLocale(Locale(lang))
        res.updateConfiguration(conf, res.displayMetrics)
        val refresh = Intent(activity, LoregActivity::class.java)
        startActivity(refresh)
        activity!!.finish()
    }

    /**
     *  To clean handler and timer when view closed
     */
    override fun onDestroyView() {
        handler.removeCallbacks(slide)
        swipeTimer.cancel()
        super.onDestroyView()
    }
}
