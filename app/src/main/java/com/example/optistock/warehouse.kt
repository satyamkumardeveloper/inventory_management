package com.example.optistock

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.optistock.databinding.ActivityWarehouseBinding
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
        val recyc=binding.recyclerview
        retrieveUserWarehouses(recyc)




















//        dataList= ArrayList<DataClass>()
//        dataList.add(DataClass("Abc","london"))
//        dataList.add(DataClass("XYZ","America"))
//        dataList.add(DataClass("def","kanpur"))
//        dataList.add(DataClass("pou","Rio"))
//        dataList.add(DataClass("mechanical","Tokyo"))
//        dataList.add(DataClass("sweet","Nepal"))
//        dataList.add(DataClass("Snacks","Bhangra"))

//        rvAdapter = RvAdapter(dataList, this)
//        binding.recyclerview.layoutManager = LinearLayoutManager(this)
//        binding.recyclerview.adapter = rvAdapter


    }
    fun retrieveUserWarehouses(recyclerView: RecyclerView) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val database = FirebaseDatabase.getInstance()
        val warehousesRef = database.getReference("users").child(currentUser?.uid ?: "").child("warehouses")

        warehousesRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val warehouses = ArrayList<DataClass>()
                for (warehouseSnapshot in snapshot.children) {
                    val warehouseName = warehouseSnapshot.child("warehouseName").getValue(String::class.java)
                    val location = warehouseSnapshot.child("location").getValue(String::class.java)

                    warehouses.add(DataClass(warehouseName.toString(), location.toString()))
                }

                val rvAdapter = RvAdapter(warehouses, this)
                recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
                recyclerView.adapter = rvAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error retrieving warehouses: ${error.message}")
            }
        })
    }



}