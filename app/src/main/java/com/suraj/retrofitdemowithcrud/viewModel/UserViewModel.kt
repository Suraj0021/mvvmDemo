package com.suraj.retrofitdemowithcrud.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suraj.retrofitdemowithcrud.model.User
import com.suraj.retrofitdemowithcrud.repository.Repository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: Repository) : ViewModel() {
    var userList: MutableLiveData<ArrayList<User>> = MutableLiveData()

    fun getAllUser() {
        viewModelScope.launch {
            try {
                val users = repository.getAllUser()

                val list = ArrayList<User>()

                for (i in users.data) {
                    list.add(i)
                }

                userList.value = list

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}