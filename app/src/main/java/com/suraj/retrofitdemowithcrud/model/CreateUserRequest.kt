package com.suraj.retrofitdemowithcrud.model

data class CreateUserRequest(val name : String,val job : String)

data class CreateUserResponse(
    val name: String,
    val job: String,
    val id: String,
    val createdAt: String
)