package com.example.td1instagram

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class SignUpActivity : AppCompatActivity() {
    lateinit var usernameEditText: EditText
    lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        val correctUsername = "test"
        val correctPassword = "123456"

        findViewById<AppCompatButton>(R.id.loginButton).setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            } else {
                if(username == correctUsername && password == correctPassword) {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("EMAIL_KEY", username)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Nom d'utilisateur ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun goToLogInPage(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
