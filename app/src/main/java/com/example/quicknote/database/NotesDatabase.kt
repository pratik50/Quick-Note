package com.example.quicknote.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun NotesDao(): NotesDao

    companion object {
        private var roomDb: NotesDatabase? = null

        fun getDb(context: Context): NotesDatabase {

            if (roomDb == null) {
                roomDb = Room.databaseBuilder(
                    context,
                    NotesDatabase::class.java,
                    "Notes.db"
                ).allowMainThreadQueries().build()
            }
            return roomDb!!
        }
    }
}