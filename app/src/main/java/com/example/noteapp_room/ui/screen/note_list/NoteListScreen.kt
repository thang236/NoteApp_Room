package com.example.noteapp_room.ui.screen.note_list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.noteapp_room.ui.screen.note_add_edit.AddEditViewModel
import com.example.noteapp_room.utils.Routes

@Composable
fun NoteListScreen(

    navController: NavHostController,
    viewModel: NoteViewModel,
    addEditViewModel: AddEditViewModel
) {

    val notes = viewModel.notes.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {

    }
    Scaffold(

        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Routes.ADD_EDIT_NOTE)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
            innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(notes.value.size) {
                NoteItem(
                    note = notes.value[it],
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            addEditViewModel.note = notes.value[it]

                            Log.d("zzzzzzz", "NoteListScreen: ${notes.value[it]}")
                            navController.navigate(Routes.ADD_EDIT_NOTE)

                        }
                        .padding(16.dp)
                )
            }
        }
    }
}