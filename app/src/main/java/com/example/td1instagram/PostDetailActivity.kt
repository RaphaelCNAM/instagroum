package com.example.td1instagram

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.squareup.picasso.Picasso

class PostDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        val title = intent.getStringExtra("POST_TITLE")
        val body = intent.getStringExtra("POST_BODY")
        val imageUrl = intent.getStringExtra("POST_IMAGE_URL")

        val titleTextView: TextView = findViewById(R.id.detailTitleTextView)
        val bodyTextView: TextView = findViewById(R.id.detailBodyTextView)
        val imageView: ImageView = findViewById(R.id.detailImageView)
        val backButtonTextView: TextView = findViewById(R.id.goBackTextView)

        titleTextView.text = title
        bodyTextView.text = body
        Picasso.get().load(imageUrl).into(imageView)

        backButtonTextView.setOnClickListener {
            finish()
        }
    }
}
