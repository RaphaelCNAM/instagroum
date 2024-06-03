package com.example.td1instagram

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PostsAdapter(private val context: Context, private val posts: List<Posts>) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private val likesMap = mutableMapOf<Int, Int>()

    init {

        posts.forEach { post ->
            likesMap[post.id] = 0
        }
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleTextView)
        val image: ImageView = itemView.findViewById(R.id.postImageView)
        val likeImageView: ImageView = itemView.findViewById(R.id.likeImageView)
        val likeCountTextView: TextView = itemView.findViewById(R.id.likeCountTextView)
        val seeArticleTextView: TextView = itemView.findViewById(R.id.seeArticleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.title.text = post.title
        Picasso.get().load(post.imageUrl).into(holder.image)

        val likes = likesMap[post.id] ?: 0
        holder.likeCountTextView.text = likes.toString()

        holder.likeImageView.setOnClickListener {

            likesMap[post.id] = likes + 1
            holder.likeCountTextView.text = (likes + 1).toString()

            holder.likeImageView.setImageResource(R.drawable.liked)
        }

        holder.seeArticleTextView.setOnClickListener {
            val intent = Intent(context, PostDetailActivity::class.java).apply {
                putExtra("POST_TITLE", post.title)
                putExtra("POST_BODY", post.body)
                putExtra("POST_IMAGE_URL", post.imageUrl)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = posts.size
}
