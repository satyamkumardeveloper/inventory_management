package com.example.optistock

import android.content.Intent
import android.health.connect.datatypes.units.Length
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.optistock.databinding.ActivityWarehouseAddBinding
import com.example.optistock.databinding.ActivityWarehouseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class warehouseAdd : AppCompatActivity() {
    private lateinit var binding: ActivityWarehouseAddBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityWarehouseAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        firebaseAuth= FirebaseAuth.getInstance()



        databaseReference=FirebaseDatabase.getInstance().getReference()
        binding.addBTN.setOnClickListener{
            val currentUser = firebaseAuth.currentUser
            var WarehouseName=binding.warehouseName.text.toString()
            var locate=binding.locationInput.text.toString()
            if (currentUser!=null){
                val userId = currentUser.uid
                val warehouseId = databaseReference.child("users").child(userId).child("warehouses").push().key
                if (WarehouseName.isNotEmpty() && locate.isNotEmpty()){
                    if (warehouseId != null) {
                        // Create a map with warehouse data
                        val warehouseData = mapOf(
                            "warehouseName" to WarehouseName,
                            "location" to locate
                        )
                        // Insert data into database
                        databaseReference.child("users").child(userId).child("warehouses").child(warehouseId).setValue(warehouseData)
                            .addOnSuccessListener {
                                startActivity(Intent(this,warehouse::class.java))
                                Toast.makeText(this,"Warehouse added successfully",Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            .addOnFailureListener { exception ->
                                Toast.makeText(this,exception.toString(),Toast.LENGTH_SHORT).show()
                            }
                    }

                }else{
                    Toast.makeText(this,"Empty feilds are not allowed",Toast.LENGTH_SHORT).show()
                }

            }   else {
                Toast.makeText(this,"Login first",Toast.LENGTH_SHORT).show()
            }
        }
    }


}
