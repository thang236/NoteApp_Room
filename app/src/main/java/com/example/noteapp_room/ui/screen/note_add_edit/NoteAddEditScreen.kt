package com.example.noteapp_room.ui.screen.note_add_edit

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.noteapp_room.ui.screen.note_list.NoteViewModel

@Composable
fun AddEditTodoScreen(
    navController: NavHostController,
    viewModel: AddEditViewModel,
    noteViewModel : NoteViewModel
) {

    var titleUp by remember { mutableStateOf("") }
    var contentUp by remember { mutableStateOf(viewModel.note?.content.toString()) }

    LaunchedEffect(key1 = true) {
        if (viewModel.note != null){
            Log.d("zzzzzz", "thang: "+ viewModel.note)
            titleUp= viewModel.note!!.title
            contentUp = viewModel.note!!.content


        }

    }


    Scaffold(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(NoteAddEditEvent.OnTitleChange("$titleUp"))
                viewModel.onEvent(NoteAddEditEvent.OnContentChange("$contentUp"))

                viewModel.onEvent(NoteAddEditEvent.OnSaveNoteClick)
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save"
                )
            }
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TextField(
                label = { Text(text = "title")},
                value = "$titleUp",
                onValueChange = {
                    titleUp = it
                },
                placeholder = {
                    Text(text = "Title")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                label = { Text(text = "content")},

                value = "$contentUp",
                onValueChange = {
                    contentUp=it
                },
                placeholder = {
                    Text(text = "Description")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
        }
    }
}