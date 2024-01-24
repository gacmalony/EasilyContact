package com.example.easilycontact.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easilycontact.MainActivity
import com.example.easilycontact.databinding.ActivityMainBinding
import com.example.easilycontact.room.UserRepository
import com.example.easilycontact.room.room
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository, val binding: ActivityMainBinding) : ViewModel(),Observable {

    val users = repository.users
    private var isUpdateOrDelete = false
    private lateinit var userUpdateOrDelete :room

    @Bindable
    var input_name = MutableLiveData<String?>()
    @Bindable
    var input_email = MutableLiveData<String?>()
    @Bindable
    var input_number= MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateData = MutableLiveData<String?>()

    @Bindable
    val deleteOrClearData = MutableLiveData<String?>()

    init {
        saveOrUpdateData.value = "Save"
        deleteOrClearData.value = "Clear All"
    }


    fun saveOrUpdate(){
        if(isUpdateOrDelete){
            //Make Update
            userUpdateOrDelete.name = input_name.value!!
            userUpdateOrDelete.email = input_email.value!!
            userUpdateOrDelete.phonenumber = input_number.value!!
            update(userUpdateOrDelete)
        }else{
            //Insert Functionality
            if(input_name.value == null && input_email.value == null && input_number== null){
                input_name = MutableLiveData<String?>()
                input_email = MutableLiveData<String?>()
                input_number = MutableLiveData<String?>()
            }
            var name = input_name.value!!
            var email = input_email.value!!
            var phnumber = input_number.value!!

            insert(room(0,name,email,phnumber))


        }


    }
    fun insert(user:room) = viewModelScope.launch{

    if(user != null){
        repository.insert(user)
        binding.editTextText.text = null
        binding.editTextText2.text = null
        binding.editTextText3.text = null
    }

    }
    fun deleteOrClearData(){
        if(isUpdateOrDelete){
            delete(userUpdateOrDelete)
        }else{
            clearAll()
        }

    }

    fun clearAll() = viewModelScope.launch{
        repository.deleteAll()
    }


    fun update(user:room)= viewModelScope.launch {

        if(user != null){

        repository.update(user)
        input_name.value = null
        input_email.value = null
        input_number.value = null
        isUpdateOrDelete = false
        saveOrUpdateData.value = "Save"
        deleteOrClearData.value = "Clear All"
            binding.editTextText.text = null
            binding.editTextText2.text = null
            binding.editTextText3.text = null

        }

    }

    fun delete(user:room)= viewModelScope.launch {
    if(user != null){


        repository.delete(user)

        input_name.value = null
        input_email.value = null
        input_number.value = null
        isUpdateOrDelete = false
        saveOrUpdateData.value = "Save"
        deleteOrClearData.value = "Clear All"
    }
    }

    fun initUpdateAndDelete(selectedItem:room){
        if (selectedItem != null){
        input_name.value = selectedItem.name
        input_email.value = selectedItem.email
        input_number.value = selectedItem.phonenumber
            userUpdateOrDelete = selectedItem
        isUpdateOrDelete   = true
        saveOrUpdateData.value = "Update"
        deleteOrClearData.value = "Delete"

        }

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}