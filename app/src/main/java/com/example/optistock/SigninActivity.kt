package com.example.optistock

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.optistock.databinding.SigninBinding
import com.google.firebase.auth.FirebaseAuth


class SigninActivity : AppCompatActivity() {
    private lateinit var binding: SigninBinding
    private lateinit var firebaseAuth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=SigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()

        binding.TextviewSignUp.setOnClickListener{
            startActivity(Intent(this,signup::class.java))
            finish()
        }
        // sign in
        binding.signIn.setOnClickListener{
            val email=binding.username.text.toString()
            val pass=binding.password.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if (it.isSuccessful){
                        startActivity(Intent(this,home::class.java))

                    }else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }

            }else{
                Toast.makeText(this,"Empty fields are not allowed",Toast.LENGTH_SHORT).show()
            }
        }
    }

//    override fun onStart() {
//        super.onStart()
//        if (firebaseAuth.currentUser!=null){
//            startActivity(Intent(this,home::class.java))
//
//        }
//    }
}