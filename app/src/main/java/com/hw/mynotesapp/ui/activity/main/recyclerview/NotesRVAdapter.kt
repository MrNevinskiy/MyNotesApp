package com.hw.mynotesapp.ui.activity.main.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hw.mynotesapp.R
import com.hw.mynotesapp.mvvm.model.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NotesRVAdapter(val onItemClick: ((Note) -> Unit)? = null) : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])

  inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) = with(itemView as CardView) {
            card_title.text = note.title
            card_body.text = note.body

            val color = when(note.color){
                Note.Color.WHITE -> R.color.color_white
                Note.Color.VIOLET -> R.color.color_violet
                Note.Color.YELLOW -> R.color.color_yello
                Note.Color.RED -> R.color.color_red
                Note.Color.PINK -> R.color.color_pink
                Note.Color.GREEN -> R.color.color_green
            }

            setCardBackgroundColor(ContextCompat.getColor(itemView.context, color))

            itemView.setOnClickListener {
                onItemClick?.invoke(note)
            }

        }
    }
}