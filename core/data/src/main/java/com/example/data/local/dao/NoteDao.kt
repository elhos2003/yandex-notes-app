package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.local.entity.NoteEntity
import com.example.model.Importance
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteEntity: NoteEntity)

    @Query("UPDATE notes SET title = :title, content = :content, color = :color, importance = :importance, self_destruct_date = :selfDestructDate WHERE uid = :uid")
    suspend fun updateByUid(
        uid: String,
        title: String,
        content: String,
        color: Int,
        importance: Importance,
        selfDestructDate: Long?
    ): Int

    @Delete
    suspend fun delete(noteEntity: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM notes WHERE uid = :uid")
    suspend fun deleteByUid(uid: String)

    @Query("DELETE FROM notes")
    suspend fun deleteAll()

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getById(id: Int): Flow<NoteEntity>

    @Query("SELECT * FROM notes WHERE uid = :uid")
    fun getByUid(uid: String): Flow<NoteEntity?>

    @Query("SELECT * FROM notes")
    fun getAll(): Flow<List<NoteEntity>>

}
