package com.example.quicknote.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.quicknote.R
import com.example.quicknote.database.Notes
import com.example.quicknote.databinding.FragmentCreateNoteBinding
import com.example.quicknote.viewmodels.NotesViewModel
import java.util.Date


class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
    private var priority = "1"
    private val viewModel: NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //For setting the toolbar title
        if (activity != null) {
            activity?.setTitle("Add new note")
        }
        // Inflate the layout for this fragment
        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)


        binding.pRed.setImageResource(R.drawable.done_icon)
        binding.pRed.setOnClickListener {
            priority = "1"
            binding.pRed.setImageResource(R.drawable.done_icon)
            binding.pBlue.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        binding.pGreen.setOnClickListener {
            priority = "2"
            binding.pGreen.setImageResource(R.drawable.done_icon)
            binding.pRed.setImageResource(0)
            binding.pBlue.setImageResource(0)
        }

        binding.pBlue.setOnClickListener {
            priority = "3"
            binding.pBlue.setImageResource(R.drawable.done_icon)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }


        binding.btnSave.setOnClickListener {
            createNotes(it)
        }



        return binding.root
    }

    private fun createNotes(it: View?) {

        val title = binding.title.text.toString()
        val subtitle = binding.subtitle.text.toString()
        val notes = binding.notes.text.toString()
        val d = Date()
        val date: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        val data = Notes(
            null,
            title = title,
            subtitle = subtitle,
            notes = notes,
            date = date.toString(),
            priority
        )
        viewModel.addNotes(data)

        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)
        Toast.makeText(requireContext(), "Note Created Successfully", Toast.LENGTH_SHORT).show()

    }

}