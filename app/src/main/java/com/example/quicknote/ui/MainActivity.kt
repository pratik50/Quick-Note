package com.example.quicknote.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.quicknote.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Fetching and setting the toolbar
        val toolbar: Toolbar = findViewById(com.example.quicknote.R.id.toolbar)
        setSupportActionBar(toolbar)

        navController = findNavController(com.example.quicknote.R.id.fragmentContainerView)

    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}




































//        noteList = ArrayList()
//        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
//
//        var observer = Observer<List<Note>> {
//            noteList = it
//
//            adapter = RvAdapter(noteList, this)
//            binding.rv.layoutManager = LinearLayoutManager(this)
//            binding.rv.adapter = adapter
//        }
//
//        viewModel.noteList.observe(this, observer)
//
//        adapter = RvAdapter(noteList, this)
//        binding.rv.layoutManager = LinearLayoutManager(this)
//        binding.rv.adapter = adapter
//
//        binding.floatingActionButton.setOnClickListener {
//            var intent = Intent(this@MainActivity, AddEditActivity::class.java)
//            startActivity(intent)
//        }
