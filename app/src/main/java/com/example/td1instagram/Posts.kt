package com.example.td1instagram

data class Posts(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val imageUrl: String // URL de l'image
)
