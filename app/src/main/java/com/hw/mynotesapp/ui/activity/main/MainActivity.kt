package com.hw.mynotesapp.ui.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.hw.mynotesapp.R
import com.hw.mynotesapp.mvvm.viewmodel.MainViewModel
import com.hw.mynotesapp.ui.activity.main.recyclerview.NotesRVAdapter
import com.hw.mynotesapp.ui.activity.note.NoteActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        rv_note.layoutManager = GridLayoutManager(this, 2)
        adapter = NotesRVAdapter {
            NoteActivity.start(this, it)
        }
        rv_note.adapter = adapter

        viewModel.getViewState().observe(this, Observer { value ->
            value?.let {
                adapter.notes = it.note
            }
        })

        fab.setOnClickListener {
            NoteActivity.start(this)
        }
    }

}