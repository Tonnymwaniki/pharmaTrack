package com.tonny.pharmatrack.repository


import com.tonny.pharmatrack.data.UserDao
import com.tonny.pharmatrack.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}