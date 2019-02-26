/*
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso
import insure.onoff.R
import insure.onoff.utilities.ONOFF_CDN_URL

/**
 * IllustrationAdapter
 *
 * This class responsible to create each view frame for slider from onoff CDN
 *
 * @author    Charles S  <charlessetiadi@onoff.insure>
 */
class IllustrationAdapter(
    private var context: Context,
    private var imageFileName: Array<String>,
    private var directoryPath: String
) : PagerAdapter() {

    /**
     * get size of url array
     */
    override fun getCount(): Int {
        return imageFileName.size
    }

    /**
     * create view object
     */
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    /**
     * load image from CDN onoff insure asset and place image to it's page
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)

        val cdnUrl = ONOFF_CDN_URL + directoryPath + imageFileName[position]

        Picasso.get()
            .load(cdnUrl)
            .placeholder(R.drawable.ic_placeholder_welcome)
            .resize(1301, 1717)
            .centerInside()
            .into(imageView)

        container.addView(imageView)

        return imageView
    }

    /**
     * clean object from view
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
