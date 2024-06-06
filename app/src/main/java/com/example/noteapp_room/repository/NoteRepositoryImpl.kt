package com.example.noteapp_room.repository

import com.example.noteapp_room.data.dao.NoteDAO
import com.example.noteapp_room.data.entity.Note
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val  dao : NoteDAO
):NoteRepository {
    override fun getAll(): Flow<List<Note>> {
        return dao.getAll()
    }

    override suspend fun upsert(note: Note) {
        dao.upsert(note)
    }

    override suspend fun delete(note: Note) {
        dao.delete(note)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

}