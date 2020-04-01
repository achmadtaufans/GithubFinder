package com.example.aiaproject.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.aiaproject.R

internal class ImageAdapter internal constructor(context: Context, private val resource: Int) : ArrayAdapter<ImageAdapter.ItemHolder>(context, resource) {

    // References to our images
    private val mThumbIds = arrayOf(R.drawable.img_aqua, R.drawable.img_tehbotol, R.drawable.img_pocari)
    private val nameProduct = arrayOf("Aqua", "Teh Botol", "Pocari Sweat")

    override fun getCount(): Int {
        return nameProduct.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val holder: ItemHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null)
            holder = ItemHolder()
            holder.name = convertView!!.findViewById(R.id.textView)
            holder.icon = convertView.findViewById(R.id.icon)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ItemHolder
        }

        holder.name!!.text = nameProduct!![position]
        holder.icon!!.setImageResource(mThumbIds[position])

        return convertView
    }

    internal class ItemHolder {
        var name: TextView? = null
        var icon: ImageView? = null
    }
}
