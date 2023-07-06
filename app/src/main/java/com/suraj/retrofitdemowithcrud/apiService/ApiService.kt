package com.suraj.retrofitdemowithcrud.apiService


import com.suraj.retrofitdemowithcrud.model.CreateUserRequest
import com.suraj.retrofitdemowithcrud.model.CreateUserResponse
import com.suraj.retrofitdemowithcrud.model.UpdateUserRequest
import com.suraj.retrofitdemowithcrud.model.UpdateUserResponse
import com.suraj.retrofitdemowithcrud.model.UsersResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // get All User List
    @GET("users")
    suspend fun getAllUsers(): Response<UsersResponse>

    // get User List By Page

    @GET("users")
    suspend fun getUsersDataByPage(@Query("page") page : Int): Response<UsersResponse>

    // Adding new User
    @POST("users")
    suspend fun createUsers(@Body createUserRequest: CreateUserRequest): Response<CreateUserResponse>

    // update user
    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") userId : Int,@Body updateUserRequest: UpdateUserRequest) : Response<UpdateUserResponse>

    // update user
    @PATCH("users/{id}")
    suspend fun updateUser2(@Path("id") userId : Int,@Body updateUserRequest: UpdateUserRequest) : Response<UpdateUserResponse>

    // Delete user
    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") userId : Int) : Response<UsersResponse>


    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}