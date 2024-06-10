package com.example.td1instagram

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditPostActivity : AppCompatActivity() {

    private lateinit var dbHelper: PostDatabaseHelper
    private var postId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_post)

        dbHelper = PostDatabaseHelper(this)

        val titleEditText: EditText = findViewById(R.id.titleEditText)
        val bodyEditText: EditText = findViewById(R.id.bodyEditText)
        val imageUrlEditText: EditText = findViewById(R.id.imageUrlEditText)
        val saveButton: Button = findViewById(R.id.saveButton)

        postId = intent.getIntExtra("POST_ID", -1)
        val postTitle = intent.getStringExtra("POST_TITLE")
        val postBody = intent.getStringExtra("POST_BODY")
        val postImageUrl = intent.getStringExtra("POST_IMAGE_URL")

        titleEditText.setText(postTitle)
        bodyEditText.setText(postBody)
        imageUrlEditText.setText(postImageUrl)

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val body = bodyEditText.text.toString()
            val imageUrl = imageUrlEditText.text.toString()

            val post = Posts(userId = 1, id = postId, title = title, body = body, imageUrl = imageUrl)
            dbHelper.updatePost(post)

            finish()
        }
    }
}
