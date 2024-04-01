package com.example.optistock

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.optistock.databinding.ActivityEachItemBinding
import com.example.optistock.databinding.ActivityUserBinding
import com.google.firebase.auth.FirebaseAuth


class user : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        firebaseAuth= FirebaseAuth.getInstance()
        binding.logoutBTN.setOnClickListener{
            startActivity(Intent(this,SigninActivity::class.java))
            firebaseAuth.signOut()
            finish()
        }
        binding.homeBTN.setOnClickListener{
            startActivity(Intent(this,home::class.java))
            finish()
        }
        binding.userBTN.setOnClickListener{
            startActivity(Intent(this,user::class.java))
            finish()
        }

    }
}