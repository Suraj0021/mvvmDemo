package com.suraj.retrofitdemowithcrud.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.suraj.retrofitdemowithcrud.R
import com.suraj.retrofitdemowithcrud.adapter.UserListAdapter
import com.suraj.retrofitdemowithcrud.apiService.ApiService
import com.suraj.retrofitdemowithcrud.databinding.ActivityMainBinding
import com.suraj.retrofitdemowithcrud.model.CreateUserRequest
import com.suraj.retrofitdemowithcrud.model.UpdateUserRequest
import com.suraj.retrofitdemowithcrud.model.User
import com.suraj.retrofitdemowithcrud.repository.Repository
import com.suraj.retrofitdemowithcrud.viewModel.UserViewModel
import com.suraj.retrofitdemowithcrud.viewModel.UserViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var userListAdapter: UserListAdapter

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        userListAdapter = UserListAdapter(arrayListOf())

        binding.rvUserList.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)

        binding.rvUserList.adapter = userListAdapter

        getAllUsersList() // get All User List


        getUserListByPage(2) // get User List By Page

        createNewUser("Suraj", "Android") // Adding new User

        updateUser(2, "Suraj", "Android Dev") // Update user Using Put

        updateUser2(2, "Suraj", "Android Dev") // Update user Using Patch

        deleteUser(2) // delete user


    }


    @SuppressLint("NotifyDataSetChanged")
    private fun getAllUsersList() {


        val userRepository = Repository(ApiService.retrofit)

        val viewModelFactory = UserViewModelFactory(userRepository)

        userViewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        userViewModel.userList.observe(this) { users ->

            showLog("${users}")



            userListAdapter.addNewList(users)


        }

        userViewModel.getAllUser()


    }

    private fun getUserListByPage(pageNum: Int) {

        GlobalScope.launch {

            try { //for handling api error

                val userListResponse = ApiService.retrofit.getUsersDataByPage(pageNum)

                showLog("User List By Page ${userListResponse.body()}")

            } catch (e: Exception) {

                showLog("${e.message}")

            }
        }

    }


    private fun createNewUser(name: String, job: String) {
        // for running on  coroutine
        GlobalScope.launch {

            try { //for handling api error

                val newUserAddedResponse =
                    ApiService.retrofit.createUsers(CreateUserRequest(name, job))

                showLog("Create User  ${newUserAddedResponse.body()}")

            } catch (e: Exception) {

                showLog("${e.message}")

            }
        }
    }

    private fun updateUser(id: Int, name: String, job: String) {
        // for running on  coroutine
        GlobalScope.launch {

            try { //for handling api error

                val updateUserResponse =
                    ApiService.retrofit.updateUser(id, UpdateUserRequest(name, job))

                showLog("Update user Put ${updateUserResponse.body()}")

            } catch (e: Exception) {

                showLog("${e.message}")

            }
        }
    }

    private fun updateUser2(id: Int, name: String, job: String) {
        // for running on  coroutine
        GlobalScope.launch {

            try { //for handling api error

                val updateUserResponse =
                    ApiService.retrofit.updateUser2(id, UpdateUserRequest(name, job))

                showLog("Update User patch ${updateUserResponse.body()}")

            } catch (e: Exception) {

                showLog("${e.message}")

            }
        }
    }


    private fun deleteUser(id: Int) {
        GlobalScope.launch {

            try { //for handling api error

                val deleteUserResponse =
                    ApiService.retrofit.deleteUser(id)

                showLog("Delete Success ${deleteUserResponse.code()}")

            } catch (e: Exception) {

                showLog("${e.message}")

            }
        }
    }

    private fun showLog(log: String) {
        Log.e("UsersData", log)
    }
}