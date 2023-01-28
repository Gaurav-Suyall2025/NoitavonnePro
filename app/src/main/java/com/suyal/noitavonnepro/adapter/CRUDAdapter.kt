package com.suyal.noitavonnepro.adapter

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.suyal.noitavonnepro.R
import com.suyal.noitavonnepro.crud.UpdateActivity
import com.suyal.noitavonnepro.db.CRUDDBHelper
import com.suyal.noitavonnepro.model.UserDataModel

/**
 * Created by Gaurav Suyal on 28,January,2023
 */
class CRUDAdapter(private var list : ArrayList<UserDataModel>,private var context: Context) : RecyclerView.Adapter<CRUDAdapter.DataViewHolder>() {



    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cus_name : TextView = itemView.findViewById(R.id.custom_name)
        val cus_email : TextView = itemView.findViewById(R.id.custom_email)
        val cus_contact : TextView = itemView.findViewById(R.id.custom_contact_number)
        val cus_com_name : TextView = itemView.findViewById(R.id.custom_company_name)
        val cus_emp_id : TextView = itemView.findViewById(R.id.custom_emp_id)
        val cardDelete : CardView = itemView.findViewById(R.id.custom_deleteBtn)
        val cardUpdate : CardView = itemView.findViewById(R.id.custom_updateBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
       val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.user_data_customer_design,parent,false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val model : UserDataModel = list[position]
        holder.cus_name.text = model.name
        holder.cus_email.text = model.email
        holder.cus_contact.text = model.contact
        holder.cus_com_name.text = model.company_name
        holder.cus_emp_id.text = model.emp_id

        holder.cardUpdate.setOnClickListener {
            val intent = Intent(context,UpdateActivity::class.java)
            intent.putExtra("UserDataModel",model)
            context.startActivity(intent)
        }


        holder.cardDelete.setOnClickListener {
            val builder : AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle("Confirmation !!!")
            builder.setMessage("Are you sure to delete ${model.name} ?")
            builder.setIcon(android.R.drawable.ic_menu_delete)
            builder.setCancelable(false)
            builder.setPositiveButton("Yes",object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    val dbHelper = CRUDDBHelper(context)

                    val result : Int = dbHelper.deleteData(model.id)

                    if (result > 0){
                        Toast.makeText(context,"Deleted Successfully",Toast.LENGTH_SHORT).show()
                        list.remove(model)
                        notifyDataSetChanged()
                    }else{
                        Toast.makeText(context,"Deletion failed",Toast.LENGTH_SHORT).show()
                    }

                }

            })
            builder.setNegativeButton("No",null)
            builder.show()
        }
    }

}