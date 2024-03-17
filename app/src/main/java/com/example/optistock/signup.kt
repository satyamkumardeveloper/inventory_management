package com.example.optistock

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.optistock.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()

        binding.TextviewSignIn.setOnClickListener{
            startActivity(Intent(this,SigninActivity::class.java))
            finish()
        }

        binding.signUp.setOnClickListener{
            val email=binding.userEmail.text.toString()
            val pass1=binding.password1.text.toString()
            val pass2=binding.password2.text.toString()
            val name=binding.usernameEntry.text.toString()
            if (email.isNotEmpty() && pass1.isNotEmpty() && pass2.isNotEmpty() && name.isNotEmpty()){
                if (pass1==pass2){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass1).addOnCompleteListener{
                        if (it.isSuccessful){
                            startActivity(Intent(this,home::class.java))

                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"Password not matches", Toast.LENGTH_SHORT).show()

                }

            }else{
                Toast.makeText(this,"Empty fields are not allowed", Toast.LENGTH_SHORT).show()
            }

        }
    }
}