package com.example.easilycontact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easilycontact.databinding.ActivityMainBinding
import com.example.easilycontact.room.UserDatabase
import com.example.easilycontact.room.UserRepository
import com.example.easilycontact.room.room
import com.example.easilycontact.viewmodel.UserViewModel
import com.example.easilycontact.viewmodel.UserViewModelFactory
import com.example.easilycontact.uiadapter.MyAdapter




class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var user: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = UserDatabase.getInstance(this).userDAO
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository, binding)

        user = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        binding.name = user

        binding.lifecycleOwner = this
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        usersList()
    }

    private fun usersList() {
        user.users.observe(this, Observer{
            binding.recyclerView.adapter = MyAdapter(it, {selectedItem: room -> listItemClicked(selectedItem)})
        })    }

    private fun listItemClicked(selectedItem: room) {
        Toast.makeText(this, "Selected name is ${selectedItem.name}", Toast.LENGTH_SHORT).show()
        user.initUpdateAndDelete(selectedItem)

    }
}