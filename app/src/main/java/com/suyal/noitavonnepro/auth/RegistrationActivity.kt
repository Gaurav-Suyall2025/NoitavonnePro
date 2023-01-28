package com.suyal.noitavonnepro.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.suyal.noitavonnepro.ProfileActivity
import com.suyal.noitavonnepro.R
import com.suyal.noitavonnepro.db.DBHelper
import com.suyal.noitavonnepro.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistrationBinding
    lateinit var dbHelper: DBHelper
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        dbHelper = DBHelper(this@RegistrationActivity)


        sharedPreferences =  getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        binding.signUpBtn.setOnClickListener {

               val userName = binding.userName.text.toString()
               val pass = binding.password.text.toString()
               val repass = binding.confirmPassword.text.toString()
               val email = binding.email.text.toString()
               val fullName = binding.fullName.text.toString()

            if (userName=="" || pass=="" || repass=="" || email=="" || fullName==""){
                Toast.makeText(this@RegistrationActivity,"Please enter all the fields",Toast.LENGTH_SHORT).show()
            }else{
                if (pass == repass){
                    val checkUser : Boolean = dbHelper.checkEmail(email)
                    if (checkUser == false){
                        val insert : Boolean = dbHelper.insertData(email,pass,userName,fullName, R.drawable.profile.toString())
                        if (insert == true){
                            editor.putBoolean("isLoggedIn",true)
                            editor.commit()
                            Toast.makeText(this@RegistrationActivity,"Registered Successfully",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@RegistrationActivity,ProfileActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this@RegistrationActivity,"Registration failed",Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this@RegistrationActivity,"User already exists! please sign in",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this@RegistrationActivity,"Passwords not matching",Toast.LENGTH_SHORT).show()
                }
            }


        }


        binding.signInText.setOnClickListener {
            val intent = Intent(this@RegistrationActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}