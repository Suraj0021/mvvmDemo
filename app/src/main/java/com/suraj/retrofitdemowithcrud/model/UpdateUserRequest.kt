package com.suraj.retrofitdemowithcrud.model

data class UpdateUserRequest(val name : String,val job : String)

data class UpdateUserResponse(val name : String,val job : String,val updatedAt : String)
