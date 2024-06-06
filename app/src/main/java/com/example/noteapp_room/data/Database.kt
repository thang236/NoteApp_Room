package com.example.noteapp_room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp_room.data.dao.NoteDAO
import com.example.noteapp_room.data.entity.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun dao(): NoteDAO

    companion object {

        @Volatile
        private var INTANCE : NoteDatabase? = null

        fun getIntance(context: Context): NoteDatabase{
            synchronized(this){
                var instance = INTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "Note.db"
                    ).build()
                }
                return instance
            }
        }

    }

}