package com.example.quicknote.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.quicknote.database.Database
import com.example.quicknote.database.Note

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var noteList: LiveData<List<Note>>

    init {
        noteList = Database.getDb(application).Dao().getAllNotes()
    }

}