package com.example.optistock

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.optistock.databinding.ActivityHomeBinding

class home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.warehouseBTN.setOnClickListener{
            startActivity(Intent(this,warehouse::class.java))
        }

    }
}