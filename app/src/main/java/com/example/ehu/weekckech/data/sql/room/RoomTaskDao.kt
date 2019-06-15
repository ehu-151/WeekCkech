package com.example.ehu.weekckech.data.sql.room

import androidx.room.*

@Dao
interface RoomTaskDao {
    // get
    @Query("SELECT * FROM roomtask ORDER BY weekGroup ASC")
    fun getAll(): List<RoomTask>

    @Query("SELECT * FROM roomtask WHERE taskId IN (:taskId)")
    fun getAllByIds(taskId: List<Int>): List<RoomTask>

    @Query("SELECT * FROM roomtask WHERE weekGroup IN (:weekGroup)")
    fun getByWeekGroup(weekGroup: List<String>): RoomTask

    @Query("SELECT * FROM roomtask WHERE detail LIKE :detail")
    fun searchLikeDetail(detail: String): RoomTask

    @Query("SELECT * FROM roomtask WHERE isChecked LIKE :isChecked")
    fun getChecked(isChecked: Boolean): List<RoomTask>


    // insert
    @Insert
    fun insert(roomTask: RoomTask)

    @Insert
    fun insertAll(vararg roomTask: RoomTask)

    // update
    @Update
    fun updateTasks(vararg roomTask: RoomTask)

    @Query("UPDATE roomtask SET isChecked = :isChecked WHERE taskId = :taskId")
    fun changeTaskCheck(taskId: Int, isChecked: Boolean)

    // delete
    @Delete
    fun delete(roomTask: RoomTask)
}