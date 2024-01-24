package com.example.easilycontact.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyTable")
data class room(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="User_id")
    var id:Int,

    @ColumnInfo(name="User_name")
    var name:String,

    @ColumnInfo(name="User_email")
    var email:String,

    @ColumnInfo(name="User_phone_number")
    var phonenumber:String
)
