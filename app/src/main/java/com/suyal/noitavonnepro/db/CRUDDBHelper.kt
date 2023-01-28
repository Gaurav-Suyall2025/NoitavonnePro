package com.suyal.noitavonnepro.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.suyal.noitavonnepro.model.UserDataModel

/**
 * Created by Gaurav Suyal on 28,January,2023
 */
class CRUDDBHelper (context: Context): SQLiteOpenHelper(context, dbName,null, DATABASE_VERSION){


    companion object{
        private val DATABASE_VERSION = 1
        private val dbName = "DataUser.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE data_user(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT,contact TEXT, company_name TEXT,emp_id TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS data_user")
        onCreate(db)
    }

    fun addStudent(model: UserDataModel) : Long{
        val db : SQLiteDatabase = writableDatabase
        val contentValues : ContentValues = ContentValues()
        contentValues.put("name",model.name)
        contentValues.put("email",model.email)
        contentValues.put("contact",model.contact)
        contentValues.put("company_name",model.company_name)
        contentValues.put("emp_id",model.emp_id)

        return db.insert("data_user",null,contentValues)
    }

    fun getAllData() : ArrayList<UserDataModel>{

        val listData = ArrayList<UserDataModel>()

        val db : SQLiteDatabase = readableDatabase
        val cursor : Cursor = db.rawQuery("SELECT * FROM data_user",null)

        if (cursor.moveToFirst()){
            do {
                val id: Int = cursor.getInt(0)
                val name : String = cursor.getString(1)
                val email : String = cursor.getString(2)
                val contact : String = cursor.getString(3)
                val company_name : String = cursor.getString(4)
                val emp_id : String = cursor.getString(5)

                val model : UserDataModel = UserDataModel(id,name,email,contact,company_name,emp_id)
                listData.add(model)

            }while (cursor.moveToNext())
        }

        return listData
    }

    fun updateUserData(model: UserDataModel): Int {
        val db : SQLiteDatabase = writableDatabase

        val contentValues : ContentValues = ContentValues()
        contentValues.put("name",model.name)
        contentValues.put("email",model.email)
        contentValues.put("contact",model.contact)
        contentValues.put("company_name",model.company_name)
        contentValues.put("emp_id",model.emp_id)

        return db.update("data_user",contentValues,"id=?", arrayOf(model.id.toString()))
    }

    fun deleteData(id: Int): Int {

        val db : SQLiteDatabase = writableDatabase
        return db.delete("data_user","id=?", arrayOf(id.toString()))

    }

}