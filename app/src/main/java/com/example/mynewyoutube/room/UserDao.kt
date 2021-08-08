package com.example.mynewyoutube.room

import android.service.autofill.UserData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(userEntity: UserEntity)

    @Query("select * from newTableUser")
    fun get(): UserEntity
}