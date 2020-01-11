package be.zvz.opendc.activities.main

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import be.zvz.opendc.R
import be.zvz.opendc.activities.article.read.ArticleReadActivity

class HitRecyclerViewAdapter(ctx: Context) : RecyclerView.Adapter<HitRecyclerViewAdapter.ViewHolder>() {
    val context = ctx
    val itemList = mutableListOf<HitRecyclerViewItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return ViewHolder(inflater.inflate(R.layout.hit_listview_item, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        holder.imageView.setImageDrawable(item.imageDrawable)
        holder.titleTextView.text = item.title
        holder.titleTextView.isSelected = true

        holder.parent.setOnClickListener {
            val articleId = itemList[position].id

            val intent = Intent(context, ArticleReadActivity::class.java)
                .putExtra("gall_id", "hit")
                .putExtra("article_id", articleId)
            context.startActivity(intent)
        }
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun addItem(image: Drawable, title: String, id: Int) {
        itemList.add(
            HitRecyclerViewItem(
                imageDrawable = image,
                title = title,
                id = id
            )
        )
    }

    class ViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        val imageView = parent.findViewById<ImageView>(R.id.hitImageView)
        val titleTextView = parent.findViewById<TextView>(R.id.hitTitleView)
        val parent = parent.findViewById<ConstraintLayout>(R.id.hitItemRootLayout)
    }
}