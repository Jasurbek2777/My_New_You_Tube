package com.example.mynewyoutube.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynewyoutube.models.Item

@Entity(tableName = "new_table")
data class EntityData (
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val items: List<Item> = ArrayList()
    )