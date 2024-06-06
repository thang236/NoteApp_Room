package com.example.noteapp_room.ui.screen.note_list

import com.example.noteapp_room.data.entity.Note

sealed class NoteListEvent {

    data class OnDeleteNote(val note: Note): NoteListEvent()


}