package com.suraj.retrofitdemowithcrud.repository

import com.suraj.retrofitdemowithcrud.apiService.ApiService
import com.suraj.retrofitdemowithcrud.model.UsersResponse

class Repository(private val apiService: ApiService) {

suspend fun getAllUser() :UsersResponse{
    val response = ApiService.retrofit.getAllUsers()

    if (  response.isSuccessful ) return response.body() as UsersResponse

    else throw Exception("Failed to fetch users: ${response.code()}")
}

}