package com.example.quicknote.repository

import androidx.lifecycle.LiveData
import com.example.quicknote.database.Notes
import com.example.quicknote.database.NotesDao

class NotesRepository(private val dao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getAllNotes()
    }

    fun gethighNotes(): LiveData<List<Notes>>{
        return dao.getHighNotes()
    }

    fun getmediumNotes(): LiveData<List<Notes>>{
        return dao.getMediumNotes()
    }

    fun getlowNotes(): LiveData<List<Notes>>{
        return dao.getLowNotes()
    }

    fun insertNotes(notes: Notes){
        dao.insert(notes)
    }

    fun deleteNotes(id: Int){
        dao.delete(id)
    }

    fun updateNotes(notes: Notes){
        dao.update(notes)
    }

}