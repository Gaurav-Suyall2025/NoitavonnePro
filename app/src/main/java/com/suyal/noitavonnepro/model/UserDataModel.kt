package com.suyal.noitavonnepro.model

import java.io.Serializable

/**
 * Created by Gaurav Suyal on 28,January,2023
 */
data class UserDataModel(
    val id: Int,
    val name: String,
    val email : String,
    val contact: String,
    val company_name : String,
    val emp_id : String
) : Serializable {

    constructor(
        name: String,
        email : String,
        contact: String,
        company_name : String,
        emp_id : String) : this(0,name,email,contact,company_name,emp_id)

}
