package com.opq.a.jetpack.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.opq.a.jetpack.ui.MyApplication

/**
 * Created by panqiang at 2019-12-21
 */
@Database(
    version = 1, entities = [
        UserEntity::class
    ]
)
abstract class JetpackDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: JetpackDatabase? = null

        fun instance(): JetpackDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(MyApplication.app, JetpackDatabase::class.java, "Jetpack.db")
                    .allowMainThreadQueries()
                    .build()
            }
        }
    }

    abstract fun userEntityDao(): UserEntityDao
}