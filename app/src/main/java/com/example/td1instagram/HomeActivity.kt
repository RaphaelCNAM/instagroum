package com.example.td1instagram

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : ComponentActivity() {

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private val TAG: String = "CHECK_RESPONSE"
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val email = intent.getStringExtra("EMAIL_KEY")
        if (email != null) {
        }

        getAllPosts()
    }

    private fun getAllPosts() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        api.getPosts().enqueue(object : Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        // Ajouter une URL d'image pour chaque post
                        val postsWithImages = posts.map {
                            it.copy(imageUrl = "https://picsum.photos/200?random=${it.id}")
                        }
                        recyclerView.adapter = PostsAdapter(this@HomeActivity, postsWithImages)
                    }
                }
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
            }
        })
    }
}
