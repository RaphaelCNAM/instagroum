package com.example.td1instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Votre code d'initialisation ici
    }
    fun goToSignUpPage(view: View) {
        // Intent pour ouvrir la page de signup
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}