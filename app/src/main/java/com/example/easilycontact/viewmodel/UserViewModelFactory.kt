package com.example.easilycontact.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.easilycontact.databinding.ActivityMainBinding
import com.example.easilycontact.room.UserRepository

class UserViewModelFactory(private val repository: UserRepository, val binding:ActivityMainBinding):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(repository, binding) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}