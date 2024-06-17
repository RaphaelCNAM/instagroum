package com.example.td1instagram

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PostsAdapter(private val context: Context, private var posts: List<Posts>) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private val dbHelper = PostDatabaseHelper(context)

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val bodyTextView: TextView = view.findViewById(R.id.bodyTextView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val likeTextView: TextView = view.findViewById(R.id.likeTextView)
        val seeArticleTextView: TextView = view.findViewById(R.id.seeArticleTextView)
        val editButton: Button = view.findViewById(R.id.editButton)
        val deleteButton: Button = view.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.titleTextView.text = post.title
        holder.bodyTextView.text = post.body

        if (post.imageUrl.isNotEmpty()) {
            Picasso.get().load(post.imageUrl).into(holder.imageView)
        } else {
            holder.imageView.setImageResource(R.drawable.heart) // Placeholder image if URL is empty
        }

        // Redimensionner l'image drawable pour le cœur
        val drawable = ContextCompat.getDrawable(context, R.drawable.heart)?.apply {
            val wrappedDrawable: Drawable = DrawableCompat.wrap(this)
            val size = 40  // Taille du cœur en pixels
            wrappedDrawable.setBounds(0, 0, size, size)
        }
        holder.likeTextView.setCompoundDrawables(drawable, null, null, null)

        holder.likeTextView.setOnClickListener {
            // handle like click
        }

        holder.seeArticleTextView.setOnClickListener {
            val intent = Intent(context, PostDetailActivity::class.java).apply {
                putExtra("POST_TITLE", post.title)
                putExtra("POST_BODY", post.body)
                putExtra("POST_IMAGE_URL", post.imageUrl)
            }
            context.startActivity(intent)
        }

        holder.editButton.setOnClickListener {
            val intent = Intent(context, EditPostActivity::class.java).apply {
                putExtra("POST_ID", post.id)
                putExtra("POST_TITLE", post.title)
                putExtra("POST_BODY", post.body)
                putExtra("POST_IMAGE_URL", post.imageUrl)
            }
            context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
            dbHelper.deletePost(post.id)
            posts = dbHelper.getAllPosts()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = posts.size
}
