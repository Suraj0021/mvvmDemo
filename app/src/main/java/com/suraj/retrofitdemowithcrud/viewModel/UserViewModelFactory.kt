package com.suraj.retrofitdemowithcrud.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suraj.retrofitdemowithcrud.repository.Repository


class UserViewModelFactory(private val userRepository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(UserViewModel::class.java))
        { return UserViewModel(userRepository) as T }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}