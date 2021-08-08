package com.example.mynewyoutube.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserEntity::class], version = 1)
    abstract class AppDataBase : RoomDatabase() {
        abstract fun dao(): UserDao

        companion object {
            private var instance: AppDataBase? = null

            fun getInstance(context: Context): AppDataBase {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "AppDataBase123"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
                return instance!!
            }
        }

}