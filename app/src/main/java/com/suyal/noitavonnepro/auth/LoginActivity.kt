package com.suyal.noitavonnepro.auth


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.suyal.noitavonnepro.ProfileActivity
import com.suyal.noitavonnepro.R
import com.suyal.noitavonnepro.crud.CRUDActivity
import com.suyal.noitavonnepro.databinding.ActivityLoginBinding
import com.suyal.noitavonnepro.db.CRUDDBHelper
import com.suyal.noitavonnepro.db.DBHelper

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = DBHelper(this@LoginActivity)

        supportActionBar!!.hide()


        binding.signInBtn.setOnClickListener {

            val user : String = binding.loginEmail.text.toString()
            val pass : String = binding.loginPassword.text.toString()

            if (user=="" || pass==""){
                Toast.makeText(this@LoginActivity,"Please enter all the fields",Toast.LENGTH_SHORT).show()
            }else{
                val checkUserPass : Boolean = dbHelper.checkEmailPassword(user,pass)
                if (checkUserPass == true){
                    Toast.makeText(this@LoginActivity,"Sign in Successful",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity,ProfileActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@LoginActivity,"Invalid Credentials",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}