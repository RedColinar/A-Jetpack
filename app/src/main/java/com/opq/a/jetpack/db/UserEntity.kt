package com.opq.a.jetpack.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by panqiang at 2019-12-21
 */
@Entity
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val userName: String
)