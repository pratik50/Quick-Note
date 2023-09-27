package com.example.quicknote

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quicknote.database.Note
import com.example.quicknote.databinding.RvItemBinding

class RvAdapter(var noteList: List<Note>, var context: Context) :
    RecyclerView.Adapter<RvAdapter.MyViewHolder>() {

    inner class MyViewHolder(var binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = RvItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.title.text = noteList[position].title
        holder.binding.description.text = noteList[position].description

        holder.itemView.setOnClickListener {
            var intent = Intent(context, AddEditActivity::class.java)
            intent.putExtra("NOTE_EDIT", noteList[position])
            context.startActivity(intent)
        }
    }
}