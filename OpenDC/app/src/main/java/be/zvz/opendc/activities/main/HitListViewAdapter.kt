package be.zvz.opendc.activities.main

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import be.zvz.opendc.R

class HitListViewAdapter : BaseAdapter() {
    val itemList = mutableListOf<HitListViewItem>()

    override fun getCount(): Int {
        return itemList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val context = parent.context

        if (view == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.hit_listview_item, parent, false)
        }

        val imageView = view!!.findViewById<ImageView>(R.id.hitImageView)
        val titleTextView = view.findViewById<TextView>(R.id.hitTitleView)

        val item = itemList[position]

        imageView.setImageDrawable(item.imageDrawable)
        titleTextView.text = item.title

        return view
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return itemList[position]
    }

    fun addItem(image: Drawable, title: String, id: Int) {
        itemList.add(
            HitListViewItem(
                imageDrawable = image,
                title = title,
                id = id
            )
        )
    }
}