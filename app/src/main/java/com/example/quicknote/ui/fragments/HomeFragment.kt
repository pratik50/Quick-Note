package com.example.quicknote.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.quicknote.R
import com.example.quicknote.adapter.NotesAdapter
import com.example.quicknote.database.Notes
import com.example.quicknote.databinding.FragmentHomeBinding
import com.example.quicknote.viewmodels.NotesViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: NotesViewModel by viewModels()
    private var oldNotes = arrayListOf<Notes>()
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Setting the toolbar title
        if (activity != null) {
            activity?.setTitle("Quick Note")
        }
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        //Search Menu inflating (Deprecated approach!!)
        setHasOptionsMenu(true)

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rcvNotes.layoutManager = staggeredGridLayoutManager

        //Inflating all notes
        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            oldNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), notesList)
            binding.rcvNotes.adapter = adapter
        }

        //High priority filter btn
        binding.btnHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvNotes.adapter = adapter

                val staggerLayout = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rcvNotes.layoutManager = staggerLayout
                adapter.notifyDataSetChanged()
            }
        }

        //Medium priority filter btn
        binding.btnMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvNotes.adapter = adapter

                val staggerLayout = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rcvNotes.layoutManager = staggerLayout
                adapter.notifyDataSetChanged()
            }
        }

        //Low priority filter btn
        binding.btnLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvNotes.adapter = adapter

                val staggerLayout = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rcvNotes.layoutManager = staggerLayout
                adapter.notifyDataSetChanged()
            }
        }

        //ShowAll priority filter btn
        binding.btnFilter.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvNotes.adapter = adapter

                val staggerLayout = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rcvNotes.layoutManager = staggerLayout
                adapter.notifyDataSetChanged()
            }
        }

        //For Creating new note btn
        binding.btnAdd.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        return binding.root
    }

    //Search Menu Item Inflating with Filtering process (deprecated approach!!)
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.menu_search)
        val searchView = item.actionView as? SearchView
        item.setIcon(R.drawable.search_icon)

        searchView?.queryHint = "Find note..."
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                notesFiltering(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFiltering(newText: String?) {
        val newFilteredList = arrayListOf<Notes>()
        for (i in oldNotes) {
            if (i.title.contains(newText!!) || i.subtitle.contains(newText)) {
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }
}