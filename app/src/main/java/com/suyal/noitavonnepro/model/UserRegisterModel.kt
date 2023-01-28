package com.suyal.noitavonnepro.model

import java.io.Serializable

/**
 * Created by Gaurav Suyal on 28,January,2023
 */
data class UserRegisterModel(
    val email : String,
    val password : String,
    val username : String,
    val fullname : String,
    val image: String

) : Serializable
