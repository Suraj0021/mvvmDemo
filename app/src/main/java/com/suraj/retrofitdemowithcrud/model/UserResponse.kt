package com.suraj.retrofitdemowithcrud.model

import java.io.Serializable

data class UsersResponse(
    val page: String,
    val per_page: String,
    val total: String,
    val total_pages: String,
    val data: ArrayList<User>
)

