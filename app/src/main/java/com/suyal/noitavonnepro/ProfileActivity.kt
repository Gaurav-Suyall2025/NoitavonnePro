package com.suyal.noitavonnepro

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import com.github.drjacky.imagepicker.ImagePicker
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.suyal.noitavonnepro.auth.RegistrationActivity
import com.suyal.noitavonnepro.crud.CRUDActivity
import com.suyal.noitavonnepro.crud.ListActivity
import com.suyal.noitavonnepro.databinding.ActivityProfileBinding
import com.suyal.noitavonnepro.db.DBHelper
import com.suyal.noitavonnepro.model.UserRegisterModel

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding

    lateinit var toggle: ActionBarDrawerToggle

    lateinit var sharedPreferences: SharedPreferences

    var uri : Uri? = null

    lateinit var list : ArrayList<UserRegisterModel>
    lateinit var dbHelper: DBHelper
//    var item : Int =0
    lateinit var headerView : View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)



        sharedPreferences =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        list = ArrayList()

        toggle = ActionBarDrawerToggle(this@ProfileActivity,binding.drawerLayout,R.string.open,R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        supportActionBar!!.title = "Profile"


        dbHelper = DBHelper(this@ProfileActivity)


        binding.profileUpdate.setOnClickListener {
            updateUser()
        }

        binding.settingProfileImage.setOnClickListener {

            ImagePicker.Companion.with(this@ProfileActivity)
                .crop()
                .start()

        }

        binding.navView.setNavigationItemSelectedListener(object : OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                item.isChecked = true

                when(item.itemId){
                    R.id.add_users ->{
                        val intent = Intent(this@ProfileActivity,CRUDActivity::class.java)
                        startActivity(intent)
                        binding.drawerLayout.closeDrawers()

                    }
                    R.id.all_users ->{
                        val intent = Intent(this@ProfileActivity,ListActivity::class.java)
                        startActivity(intent)
                        binding.drawerLayout.closeDrawers()
                    }
                }
                return true
            }

        })

        list = dbHelper.getUserData()

         headerView = binding.navView.getHeaderView(0)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

             uri = data!!.data
            binding.settingProfileImage.setImageURI(uri)

    }


    private fun updateUser() {
        val username : String = binding.profileUsername.text.toString()
        val email : String = binding.profileEmail.text.toString()
        val password : String = binding.profilePassword.text.toString()
        val fullname : String = binding.profileFullName.text.toString()

        val model : UserRegisterModel = UserRegisterModel(email,password,username,fullname,uri.toString())

        val updateUser : Boolean = dbHelper.updateData(model)
        if (updateUser == true){
            Toast.makeText(this@ProfileActivity,"Updation Successfully",Toast.LENGTH_SHORT).show()
        }


    }

    override fun onStart() {
        super.onStart()

        list = dbHelper.getUserData()

        binding.profileEmail.setText(list[0].email)
        binding.profileUsername.setText(list[0].username)
        binding.profileFullName.setText(list[0].fullname)
        binding.profilePassword.setText(list[0].password)


        binding.settingProfileImage.setImageURI(list[0].image.toUri())


        headerView.findViewById<TextView>(R.id.user_name).setText(list[0].fullname)
        headerView.findViewById<ImageView>(R.id.profileimg).setImageURI(list[0].image.toUri())
    }
}