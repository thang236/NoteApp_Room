package com.example.noteapp_room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteapp_room.data.NoteDatabase
import com.example.noteapp_room.repository.NoteRepositoryImpl
import com.example.noteapp_room.ui.screen.note_add_edit.AddEditTodoScreen
import com.example.noteapp_room.ui.screen.note_add_edit.AddEditViewModel
import com.example.noteapp_room.ui.screen.note_list.NoteListScreen
import com.example.noteapp_room.ui.screen.note_list.NoteViewModel
import com.example.noteapp_room.ui.theme.NoteApp_RoomTheme
import com.example.noteapp_room.utils.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteApp_RoomTheme {
                val context = LocalContext.current
                val db = NoteDatabase.getIntance(context)
                val repositoryImpl = NoteRepositoryImpl(db.dao())


                val addEditViewModel = AddEditViewModel( repositoryImpl )
                val noteViewModel = NoteViewModel(repositoryImpl)


                var navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = Routes.NOTE_LIST){

                    composable(Routes.NOTE_LIST){
                        NoteListScreen(navController = navController
                            , viewModel = noteViewModel,
                            addEditViewModel = addEditViewModel)
                    }

                    composable(
                        route = Routes.ADD_EDIT_NOTE
                    ) {
                        AddEditTodoScreen(
                            navController = navController,
                            viewModel = addEditViewModel,
                            noteViewModel = noteViewModel
                        )
                    }

                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteApp_RoomTheme {
        Greeting("Android")
    }
}