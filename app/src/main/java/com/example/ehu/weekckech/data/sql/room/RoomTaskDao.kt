package com.example.ehu.weekckech.data.sql.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomTaskDao{
    // get
    @Query("SELECT * FROM roomtask")
    fun getAll(): List<RoomTask>

    @Query("SELECT * FROM roomtask WHERE taskId IN (:taskId)")
    fun getAllByIds(taskId: List<Int>): List<RoomTask>

    @Query("SELECT * FROM roomtask WHERE weekGroup IN (:weekGroup)")
    fun getByWeekGroup(weekGroup: List<String>): RoomTask

    @Query("SELECT * FROM roomtask WHERE detail LIKE :detail")
    fun searchLikeDetail(detail:String)

    @Query("SELECT * FROM roomtask WHERE isChecked LIKE :isChecked")
    fun getChecked(isChecked:Boolean)


    // insert
    @Insert
    fun insertAll(vararg roomTask: RoomTask)

    // update
    fun updateTasks(vararg roomTask:RoomTask)

    // delete
    @Delete
    fun delete(roomTask: RoomTask)

}