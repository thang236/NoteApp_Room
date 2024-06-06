package com.example.noteapp_room.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.noteapp_room.data.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Query("Select * from Note")
    fun getAll() : Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun upsert(note: Note)

    @Delete
     fun delete(note: Note)

    @Query("SELECT * FROM Note WHERE id = :id")
    fun getNoteById(id: Int): Note?
}