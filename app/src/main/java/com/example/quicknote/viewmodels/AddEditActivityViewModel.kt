package com.example.quicknote.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.quicknote.database.Database
import com.example.quicknote.database.Note

class AddEditActivityViewModel(application: Application) : AndroidViewModel(application) {

    fun insert(note: Note, context: Context) {
        Database.getDb(context).Dao().insert(note)
    }

    fun update(note: Note, context: Context) {
        Database.getDb(context).Dao().update(note)
    }

}