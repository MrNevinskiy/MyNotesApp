package com.hw.mynotesapp.ui.activity.main.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hw.mynotesapp.R
import com.hw.mynotesapp.mvvm.model.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteRVAdapter : RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    var note: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount() = note.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(note[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) = with(itemView) {
            card_title.text = note.title
            card_body.text = note.text
            setBackgroundColor(note.color)
        }
    }
}