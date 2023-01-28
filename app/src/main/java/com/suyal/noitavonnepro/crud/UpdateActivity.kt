package com.suyal.noitavonnepro.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.suyal.noitavonnepro.R
import com.suyal.noitavonnepro.databinding.ActivityUpdateBinding
import com.suyal.noitavonnepro.db.CRUDDBHelper
import com.suyal.noitavonnepro.model.UserDataModel

class UpdateActivity : AppCompatActivity() {

    lateinit var binding : ActivityUpdateBinding
    var id: Int = 0
    lateinit var dbHelper : CRUDDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = CRUDDBHelper(this@UpdateActivity)

        val model : UserDataModel = intent.extras!!.getSerializable("UserDataModel") as UserDataModel

        id = model.id

        binding.updateCrudUsername.setText(model.name)
        binding.updateCrudEmail.setText(model.email)
        binding.updateCrudContactNumber.setText(model.contact)
        binding.updateCrudCompanyName.setText(model.company_name)
        binding.updateCrudEmployeeId.setText(model.emp_id)

        binding.crudUpdateBtn.setOnClickListener {
            updateData()
        }
    }


    //update data in the database
    fun updateData(){
        val name : String = binding.updateCrudUsername.text.toString()
        val email : String = binding.updateCrudEmail.text.toString()
        val contact : String = binding.updateCrudContactNumber.text.toString()
        val companyName : String = binding.updateCrudCompanyName.text.toString()
        val empID : String = binding.updateCrudEmployeeId.text.toString()

        val model : UserDataModel = UserDataModel(id,name,email,contact,companyName,empID)

        val result : Int = dbHelper.updateUserData(model)
        if (result > 0){
            Toast.makeText(this@UpdateActivity,"Updated Successfully",Toast.LENGTH_SHORT).show()
            finish()
        }else{
            Toast.makeText(this@UpdateActivity,"Updation Failed",Toast.LENGTH_SHORT).show()
        }
    }
}