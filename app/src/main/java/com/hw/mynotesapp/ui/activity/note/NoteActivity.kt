package com.hw.mynotesapp.ui.activity.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.hw.mynotesapp.R
import com.hw.mynotesapp.mvvm.model.Note
import com.hw.mynotesapp.mvvm.view.NoteViewState
import com.hw.mynotesapp.mvvm.viewmodel.NoteViewModel
import com.hw.mynotesapp.ui.activity.base.BaseActivity
import kotlinx.android.synthetic.main.note_activity.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : BaseActivity<Note?, NoteViewState>() {
    companion object {
        private const val NOTE_KEY = "note"
        private const val DATE_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, noteId: String? = null) = Intent(context, NoteActivity::class.java).apply {
            putExtra(NOTE_KEY, noteId)
            context.startActivity(this)
        }
    }

    override val layoutRes: Int = R.layout.note_activity
    private var note: Note? = null
    override val viewModel by lazy {
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }

    val textWatcher = object:TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            saveNote()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val noteId = intent.getStringExtra(NOTE_KEY)

        noteId?.let {
            viewModel.loadNote(it)
        } ?: let {
            supportActionBar?.title =  getString(R.string.new_note)
            initView()
        }
    }

    override fun renderData(data: Note?) {
        this.note = data
        supportActionBar?.title = note?.let {
            SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(it.lastChanged)
        } ?: getString(R.string.new_note)
        initView()
    }

  private fun initView(){
      titleEt.removeTextChangedListener(textWatcher)
      bodyEt.removeTextChangedListener(textWatcher)

      note?.let{
          titleEt.setText(it.title)
          bodyEt.setText(it.body)
          val color = when(it.color){
              Note.Color.WHITE -> R.color.color_white
              Note.Color.VIOLET -> R.color.color_violet
              Note.Color.YELLOW -> R.color.color_yello
              Note.Color.RED -> R.color.color_red
              Note.Color.PINK -> R.color.color_pink
              Note.Color.GREEN -> R.color.color_green
          }
          toolbar.setBackgroundColor(ResourcesCompat.getColor(resources,color, null))
      }

      titleEt.addTextChangedListener(textWatcher)
      bodyEt.addTextChangedListener(textWatcher)
  }

    private fun  saveNote(){
        titleEt.text?.let {
            if (it.length <3) return
        } ?: return

        note = note?.copy(
            title = titleEt.text.toString(),
            body = bodyEt.text.toString(),
            lastChanged = Date()
        )?: Note(UUID.randomUUID().toString(),titleEt.text.toString(),bodyEt.text.toString())

        note?.let { viewModel.save(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}