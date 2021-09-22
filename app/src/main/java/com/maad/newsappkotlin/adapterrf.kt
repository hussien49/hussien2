package com.maad.newsappkotlin

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter(val activity: Activity, val articles: ArrayList<Article>?) :
    RecyclerView.Adapter<Adapter.Holder>() {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.tv)
        val image: ImageView = view.findViewById(R.id.iv)
        val parent: CardView = view.findViewById(R.id.card_view)
        val description: TextView = view.findViewById(R.id.description)
        val score: TextView = view.findViewById(R.id.Score)
        val name: TextView = view.findViewById(R.id.name)
        val episode : TextView = view.findViewById(R.id.name)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder{
        val view = activity.layoutInflater.inflate(R.layout.news_list_item, parent, false)
        val holder = Holder(view)
        return holder
    }

    override fun onBindViewHolder(holder:Holder, position: Int) {
         holder.score.setText(articles?.get(position)?.score)
         holder.name.setText(articles?.get(position)?.name)
        holder.description.setText(articles?.get(position)?.description)
        holder.episode.setText(articles?.get(position)?.episodes)


        holder.parent.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(articles?.get(position)?.url))
            activity.startActivity(intent)
            holder.image.setOnClickListener {
                articles?.get(position)?.url
            }
            holder.episode.setOnClickListener {
                articles?.get(position)?.episodes
            }
            }

    }

    override fun getItemCount() = articles?.size ?: 0


}


