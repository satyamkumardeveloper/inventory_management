package com.example.optistock

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.optistock.databinding.ActivityWarehouseBinding
import com.google.android.material.search.SearchView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class warehouse : AppCompatActivity() {
    private lateinit var binding: ActivityWarehouseBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var rvAdapter: RvAdapter
    private lateinit var dataList: ArrayList<DataClass>
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWarehouseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        binding.floatingImageView.setOnClickListener {
            startActivity(Intent(this, warehouseAdd::class.java))
        }




















        dataList= ArrayList<DataClass>()
        dataList.add(DataClass("Abc","london"))
        dataList.add(DataClass("XYZ","America"))
        dataList.add(DataClass("def","kanpur"))
        dataList.add(DataClass("pou","Rio"))
        dataList.add(DataClass("mechanical","Tokyo"))
        dataList.add(DataClass("sweet","Nepal"))
        dataList.add(DataClass("Snacks","Bhangra"))

        rvAdapter = RvAdapter(dataList, this)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = rvAdapter


    }
}