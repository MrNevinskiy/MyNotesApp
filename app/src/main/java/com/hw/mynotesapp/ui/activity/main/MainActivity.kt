package com.hw.mynotesapp.ui.activity.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.hw.mynotesapp.R
import com.hw.mynotesapp.mvvm.model.Note
import com.hw.mynotesapp.mvvm.view.MainViewState
import com.hw.mynotesapp.mvvm.viewmodel.MainViewModel
import com.hw.mynotesapp.ui.activity.base.BaseActivity
import com.hw.mynotesapp.ui.activity.main.recyclerview.NotesRVAdapter
import com.hw.mynotesapp.ui.activity.note.NoteActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override val layoutRes: Int = R.layout.activity_main

    lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rv_note.layoutManager = GridLayoutManager(this, 2)
        adapter = NotesRVAdapter {
            NoteActivity.start(this, it.id)
        }

        rv_note.adapter = adapter

        fab.setOnClickListener {
            NoteActivity.start(this)
        }
    }

    override fun renderData(data: List<Note>?) {
        data?.let {
            adapter.notes = it
        }
    }

}