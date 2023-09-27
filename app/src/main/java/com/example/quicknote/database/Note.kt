package com.example.quicknote.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Note(
    //room database autogenerated key
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var title: String,
    var description: String
) : Serializable