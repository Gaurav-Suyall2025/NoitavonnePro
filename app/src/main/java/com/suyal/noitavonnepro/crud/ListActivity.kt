package com.suyal.noitavonnepro.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suyal.noitavonnepro.R
import com.suyal.noitavonnepro.adapter.CRUDAdapter
import com.suyal.noitavonnepro.databinding.ActivityListBinding
import com.suyal.noitavonnepro.db.CRUDDBHelper
import com.suyal.noitavonnepro.model.UserDataModel

class ListActivity : AppCompatActivity() {

    lateinit var binding: ActivityListBinding
    lateinit var list : ArrayList<UserDataModel>
    lateinit var dbHelper : CRUDDBHelper
    lateinit var crudAdapter: CRUDAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.back_icon)

        supportActionBar!!.title = "List of Users"

        dbHelper = CRUDDBHelper(this@ListActivity)



    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onStart() {
        super.onStart()

        list = dbHelper.getAllData()

        //adapter
        crudAdapter = CRUDAdapter(list,this@ListActivity)

        //setting adapter
        binding.recyclerView.adapter = crudAdapter
        val linearLayoutManager : LinearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        binding.recyclerView.layoutManager = linearLayoutManager

    }
}