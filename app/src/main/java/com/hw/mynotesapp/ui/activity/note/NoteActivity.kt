package com.hw.mynotesapp.ui.activity.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.hw.mynotesapp.R
import com.hw.mynotesapp.mvvm.model.Note
import com.hw.mynotesapp.mvvm.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.item_note.*
import kotlinx.android.synthetic.main.note_activity.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {
    companion object {
        private const val NOTE_KEY = "note"
        private const val DATE_FORMAT = "dd.MM.yy HH.mm"

        fun start(context: Context, note: Note? = null) = Intent(context, NoteActivity::class.java).apply {
            putExtra(NOTE_KEY, note)
            context.startActivity(this)
        }
    }

        private var  note: Note? = null
        lateinit var  viewModel: NoteViewModel

    val textWatcher = object:TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {
            saveNote()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_activity)

        note = intent.getParcelableExtra(NOTE_KEY)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        supportActionBar?.title = note?.let {
            SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(it.lastChanged)
        } ?: getString(R.string.new_note)
        initView()
    }

  private fun initView(){
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

        note?.let { viewModel.saveChanges(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}