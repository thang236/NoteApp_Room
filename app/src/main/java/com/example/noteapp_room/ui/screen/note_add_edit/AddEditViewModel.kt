package com.example.noteapp_room.ui.screen.note_add_edit

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp_room.data.entity.Note
import com.example.noteapp_room.repository.NoteRepositoryImpl

import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch


class AddEditViewModel(
    private val repositoryImpl: NoteRepositoryImpl,
):ViewModel() {
    var note by mutableStateOf(Note(-1,"",""))

    private var title by mutableStateOf("")

    var content by mutableStateOf("")
        private set





    fun  onEvent(event: NoteAddEditEvent){
        when(event) {
            is NoteAddEditEvent.OnTitleChange -> {
                title = event.title
            }
            is NoteAddEditEvent.OnContentChange ->{
                content = event.content
            }
            is NoteAddEditEvent.OnSaveNoteClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    if (note.id!= -1){
                        Log.d("111111111111", "onEvent: $note")
                        note.content = content
                        note.title = title
                        repositoryImpl.upsert(
                            note
                        )
                        note = Note(-1,"","")
                        title=""
                        content=""
                    }else{
                        Log.d("22222222222", "onEvent: ")
                        repositoryImpl.upsert(
                            Note(
                                title = title,
                                content = content
                            )
                        )

                        note = Note(-1,"","")
                        title=""
                        content=""
                    }

                }
                }
            }
        }
    }



