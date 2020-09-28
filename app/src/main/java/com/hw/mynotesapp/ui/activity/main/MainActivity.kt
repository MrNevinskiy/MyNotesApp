package com.hw.mynotesapp.ui.activity.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.Auth
import com.hw.mynotesapp.R
import com.hw.mynotesapp.mvvm.model.Note
import com.hw.mynotesapp.mvvm.model.provider.FirestoreProvider
import com.hw.mynotesapp.mvvm.view.MainViewState
import com.hw.mynotesapp.mvvm.viewmodel.MainViewModel
import com.hw.mynotesapp.ui.activity.base.BaseActivity
import com.hw.mynotesapp.ui.activity.main.recyclerview.NotesRVAdapter
import com.hw.mynotesapp.ui.activity.note.NoteActivity
import com.hw.mynotesapp.ui.activity.splash.SplashActivity
import com.hw.mynotesapp.ui.common.LogoutDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<List<Note>?, MainViewState>(), LogoutDialog.LogoutListener {

    val firestoreProvider: FirestoreProvider by inject()

    companion object{
        fun start(context: Context) = Intent(context, MainActivity::class.java).apply {
            context.startActivity(this)
        }
    }

    override val viewModel: MainViewModel by viewModel()
    override val layoutRes: Int = R.layout.activity_main
    lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
        MenuInflater(this).inflate(R.menu.main, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId){
            R.id.logout -> showLogoutDialog().let{true}
            else -> false
        }

    fun showLogoutDialog(){
        supportFragmentManager.findFragmentByTag(LogoutDialog.TAG)?: LogoutDialog().show(supportFragmentManager, LogoutDialog.TAG)
    }

    override fun onLogout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener{
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
    }
}