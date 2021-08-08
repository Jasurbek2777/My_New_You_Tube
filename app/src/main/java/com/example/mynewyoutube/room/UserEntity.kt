package com.example.mynewyoutube.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "newTableUser")

data class UserEntity(
    var name: String? = null,
    var photoUrl: String? = null,
    var gmail: String? = null,
    @PrimaryKey
    var uid: String = "123123",
    var lastSeen: String? = "",
    var online: Boolean? = false,
    var signed: Boolean? = null
) : Serializable