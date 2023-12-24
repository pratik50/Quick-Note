package com.example.quicknote.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.quicknote.R
import com.example.quicknote.database.Notes
import com.example.quicknote.databinding.RvItemBinding
import com.example.quicknote.ui.fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, private var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    //Filtering notes fun
    @SuppressLint("NotifyDataSetChanged")
    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }
    class NotesViewHolder(val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            RvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.notesTitle.text = data.title
        holder.binding.notesSubtitle.text = data.subtitle
        holder.binding.noteDate.text = data.date

        when(data.priority){
            "1" -> {
                holder.binding.priority.setBackgroundResource(R.drawable.red_dot)
            }
            "2" -> {
                holder.binding.priority.setBackgroundResource(R.drawable.green_dot)
            }
            "3" -> {
                holder.binding.priority.setBackgroundResource(R.drawable.blue_dot)
            }
        }

        //Navigating particular note to editing fragment
        holder.binding.root.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }
}