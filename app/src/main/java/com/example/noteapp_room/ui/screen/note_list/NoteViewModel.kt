package com.example.noteapp_room.ui.screen.note_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp_room.data.entity.Note
import com.example.noteapp_room.repository.NoteRepositoryImpl
import com.example.noteapp_room.ui.screen.note_add_edit.AddEditViewModel
import com.example.noteapp_room.utils.Routes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val repositoryImpl: NoteRepositoryImpl
): ViewModel() {
    var note: Note? = null
    val notes = repositoryImpl.getAll()



    fun onEvent(event: NoteListEvent){
        when (event) {
            is NoteListEvent.OnDeleteNote ->{
                    viewModelScope.launch(Dispatchers.IO) {
                        repositoryImpl.delete(event.note)
                    }
                }


        }
    }




}