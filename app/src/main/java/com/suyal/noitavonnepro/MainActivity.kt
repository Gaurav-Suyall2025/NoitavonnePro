package com.suyal.noitavonnepro

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suyal.noitavonnepro.auth.RegistrationActivity
import com.suyal.noitavonnepro.databinding.ActivityMainBinding
import com.suyal.noitavonnepro.db.DBHelper
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()



        sharedPreferences =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        Timer().schedule(object : TimerTask() {
            override fun run() {

                val res : Boolean = sharedPreferences.getBoolean("isLoggedIn",false)

                if (res == true) {

                    val intent =
                        Intent(this@MainActivity, ProfileActivity::class.java)
                    startActivity(intent)

                } else {
                    val intent =
                        Intent(this@MainActivity, RegistrationActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }
        }, 1000L)


    }
}