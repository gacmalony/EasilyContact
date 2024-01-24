package com.example.easilycontact.room

class UserRepository(private val dao:UserDAO) {

    val users = dao.getAllUsersInDatabase()

    suspend fun insert(user:room): Long{
        return dao.insertUser(user)
    }
    suspend fun delete(user: room) {
        return dao.deleteUser(user)
    }

    suspend fun update(user:room) {
        return dao.updateUser(user)
    }

    suspend fun deleteAll(){
        return dao.deleteAll()
    }


}