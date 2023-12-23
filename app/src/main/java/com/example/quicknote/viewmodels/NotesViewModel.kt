package com.example.quicknote.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.quicknote.database.Notes
import com.example.quicknote.database.NotesDatabase
import com.example.quicknote.repository.NotesRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDb(application).NotesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }

    fun getHighNotes(): LiveData<List<Notes>>{
        return repository.gethighNotes()
    }

    fun getMediumNotes(): LiveData<List<Notes>>{
        return repository.getmediumNotes()
    }

    fun getLowNotes(): LiveData<List<Notes>>{
        return repository.getlowNotes()
    }

    fun deleteNote(id: Int){
        repository.deleteNotes(id)
    }

}