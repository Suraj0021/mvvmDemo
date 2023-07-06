package com.suraj.retrofitdemowithcrud.adapter


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.suraj.retrofitdemowithcrud.R
import com.suraj.retrofitdemowithcrud.databinding.UserviewBinding
import com.suraj.retrofitdemowithcrud.model.User

class UserListAdapter(var userList: ArrayList<User>) :
    RecyclerView.Adapter<UserListAdapter.UserHolder>() {

   inner class UserHolder(view : View) : RecyclerView.ViewHolder(view) {

       val binding : UserviewBinding
       init {
           binding = UserviewBinding.bind(view)
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.userview, parent,false)
        )
    }

    override fun getItemCount() =  userList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserHolder, position: Int) {

        val data = userList[position]

        holder.binding.user = data

        showLog("$data")

        holder.binding.txtUserName.text = "${data.first_name} ${data.last_name}"

        Picasso.get()
            .load(data.avatar)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(holder.binding.imgAvatar)
    }

    private fun showLog(log: String) {
        Log.e("UsersDataInAdapter", log)
    }

    fun addNewList(userList2 : ArrayList<User>){
        userList.clear()
        userList.addAll(userList2)
        notifyDataSetChanged()
    }
}
