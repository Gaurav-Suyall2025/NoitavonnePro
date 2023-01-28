package com.suyal.noitavonnepro.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import com.suyal.noitavonnepro.model.UserRegisterModel

/**
 * Created by Gaurav Suyal on 27,January,2023
 */
class DBHelper(context: Context) : SQLiteOpenHelper(context, dbName,null, DATABASE_VERSION) {


    companion object{
        private val DATABASE_VERSION = 1
        private val dbName = "LoginRegister.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("create Table userData(email TEXT primary key,password TEXT,username TEXT,fullname TEXT,image TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("drop Table if exists userData")
    }


    fun insertData(email : String, password: String,userName : String,fullName : String,image: String) : Boolean{
        val db : SQLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("email",email)
        contentValues.put("password",password)
        contentValues.put("username",userName)
        contentValues.put("fullname",fullName)
        contentValues.put("image",image)
        val result : Long = db.insert("userData",null,contentValues)
        if (result == -1L) return false
        else
            return true
    }

    public fun checkEmail(email: String): Boolean{
        val mydb : SQLiteDatabase = this.writableDatabase
        val cursor : Cursor = mydb.rawQuery("Select * from userData where email = ?", arrayOf(email))
        if (cursor.count > 0)
            return true
        else
            return false
    }

    public fun checkEmailPassword(email: String,password: String) : Boolean{
        val mydb : SQLiteDatabase = this.writableDatabase
        val cursor : Cursor = mydb.rawQuery("Select * from userData where email = ? and password = ?",
            arrayOf(email,password))
        if (cursor.count > 0)
            return true
        else
            return false
    }

    fun updateData(model: UserRegisterModel) : Boolean{
        val db : SQLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("email",model.email)
        contentValues.put("password",model.password)
        contentValues.put("username",model.username)
        contentValues.put("fullname",model.fullname)
        contentValues.put("image",model.image)
        val whereClause = "email=?"
        val whereArgs = arrayOf(model.email)
        val result : Int = db.update("userData",contentValues,whereClause,whereArgs)
        if (result == -1) return false
        else
            return true
    }
    
    fun getUserData() : ArrayList<UserRegisterModel>{
        val listData = ArrayList<UserRegisterModel>()

        val db : SQLiteDatabase = readableDatabase
        val cursor : Cursor = db.rawQuery("SELECT * FROM userData",null)
        if (cursor.moveToFirst()){
            do {
                val email : String = cursor.getString(0)
                val password : String = cursor.getString(1)
                val userName : String = cursor.getString(2)
                val fullName : String = cursor.getString(3)
                val image : String = cursor.getString(4)

                val model : UserRegisterModel = UserRegisterModel(email,password,userName,fullName,image)
                listData.add(model)
            }while (cursor.moveToNext())
        }

        return  listData
    }

}