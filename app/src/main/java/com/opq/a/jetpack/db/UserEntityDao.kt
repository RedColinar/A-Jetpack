package com.opq.a.jetpack.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

/**
 * Created by panqiang at 2019-12-21
 */
@Dao
interface UserEntityDao {

    @Query("select * from UserEntity where id=:id")
    fun getById(id: String): UserEntity?

    @Query("select * from UserEntity where id=:id")
    fun getLiveDataById(id: String): LiveData<UserEntity>

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg user: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)
}