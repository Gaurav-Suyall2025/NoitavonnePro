package com.suyal.noitavonnepro.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.Toast
import com.suyal.noitavonnepro.R
import com.suyal.noitavonnepro.auth.RegistrationActivity
import com.suyal.noitavonnepro.databinding.ActivityCrudactivityBinding
import com.suyal.noitavonnepro.db.CRUDDBHelper
import com.suyal.noitavonnepro.model.UserDataModel

class CRUDActivity : AppCompatActivity() {

    lateinit var binding: ActivityCrudactivityBinding
    lateinit var dbHelper: CRUDDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrudactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.back_icon)

        supportActionBar!!.title = "Add Users"

        dbHelper = CRUDDBHelper(this@CRUDActivity)

        binding.crudSaveBtn.setOnClickListener {
            saveData()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


    //to save the data in the SQLite DB
    private fun saveData() {

        val name : String = binding.crudUsername.text.toString()
        val email : String = binding.crudEmail.text.toString()
        val contact : String = binding.crudContactNumber.text.toString()
        val companyName : String = binding.crudCompanyName.text.toString()
        val empID : String = binding.crudEmployeeId.text.toString()

        val model : UserDataModel = UserDataModel(name,email,contact,companyName,empID)

        if (name=="" || email=="" || contact=="" ||companyName=="" || empID==""){
            Toast.makeText(this@CRUDActivity,"Please enter all the fields",Toast.LENGTH_SHORT).show()
        }else{
            val result : Long = dbHelper.addStudent(model)

            if (result > 0){
                Toast.makeText(this@CRUDActivity,"Data Saved Successfully",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@CRUDActivity,ListActivity::class.java))
            }else{
                Toast.makeText(this@CRUDActivity,"Failed $result",Toast.LENGTH_SHORT).show()
            }
        }

    }
}