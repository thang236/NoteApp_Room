package com.example.noteapp_room.repository

import com.example.noteapp_room.data.entity.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAll() : Flow<List<Note>>

    suspend fun upsert(note: Note)

    suspend fun delete(note: Note)

    suspend fun getNoteById(id: Int): Note?
}