package com.example.quicknote.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.quicknote.R
import com.example.quicknote.database.Notes
import com.example.quicknote.databinding.FragmentEditNotesBinding
import com.example.quicknote.viewmodels.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Date

class EditNotesFragment : Fragment() {

    val oldNotes by navArgs<EditNotesFragmentArgs>()
    private lateinit var binding: FragmentEditNotesBinding
    private var priority: String = "1"
    private val viewModel: NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //For setting the toolbar title
        if (activity != null) {
            activity?.setTitle("Update note")
        }
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.delete_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_delete -> {
                        val bottomSheet = BottomSheetDialog(requireContext())
                        bottomSheet.setContentView(R.layout.dialog_delete)

                        val textViewYes = bottomSheet.findViewById<TextView>(R.id.btn_yes)
                        val textViewNo = bottomSheet.findViewById<TextView>(R.id.btn_no)

                        textViewYes?.setOnClickListener {
                            viewModel.deleteNote(oldNotes.data.id!!)
                            bottomSheet.show()
                        }

                        textViewNo?.setOnClickListener {
                            bottomSheet.dismiss()
                        }
                        bottomSheet.show()
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.editTitle.setText(oldNotes.data.title)
        binding.editSubtitle.setText(oldNotes.data.subtitle)
        binding.editNotes.setText(oldNotes.data.notes)

        when (oldNotes.data.priority) {
            "1" -> {
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.done_icon)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }

            "2" -> {
                priority = "2"
                binding.pYellow.setImageResource(R.drawable.done_icon)
                binding.pGreen.setImageResource(0)
                binding.pRed.setImageResource(0)
            }

            "3" -> {
                priority = "3"
                binding.pRed.setImageResource(R.drawable.done_icon)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
        }

        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.done_icon)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.done_icon)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.done_icon)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        binding.btnUpdate.setOnClickListener {
            update(it)
        }

        return binding.root

    }

    private fun update(it: View?) {

        val title = binding.editTitle.text.toString()
        val subtitle = binding.editSubtitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val d = Date()
        val date: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        val data = Notes(
            id = oldNotes.data.id,
            title = title,
            subtitle = subtitle,
            notes = notes,
            date = date.toString(),
            priority
        )
        viewModel.updateNotes(data)

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
        Toast.makeText(requireContext(), "Note Updated Successfully", Toast.LENGTH_SHORT).show()
    }

}