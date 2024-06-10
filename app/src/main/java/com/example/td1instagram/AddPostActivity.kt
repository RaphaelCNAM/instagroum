package com.example.td1instagram

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        val titleEditText: EditText = findViewById(R.id.titleEditText)
        val bodyEditText: EditText = findViewById(R.id.bodyEditText)
        val imageUrlEditText: EditText = findViewById(R.id.imageUrlEditText)
        val saveButton: Button = findViewById(R.id.saveButton)

        val dbHelper = PostDatabaseHelper(this)

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val body = bodyEditText.text.toString()
            val imageUrl = imageUrlEditText.text.toString()

            val post = Posts(userId = 1, id = 0, title = title, body = body, imageUrl = imageUrl)
            dbHelper.addPost(post)

            finish()
        }
    }
}
