package com.example.noteapp_room.ui.screen.note_add_edit

sealed class NoteAddEditEvent {
    data class  OnTitleChange(val title: String): NoteAddEditEvent()
    data class  OnContentChange(val content: String): NoteAddEditEvent()
    data object OnSaveNoteClick: NoteAddEditEvent()

}