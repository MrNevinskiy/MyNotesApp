package com.hw.mynotesapp.mvvm.model

object NoteRepository {

    private val note: List<Note> = listOf(
        Note(
            "Первая заметка",
            "Текст первой заметки.",
            0xfff06292.toInt()
        ),
        Note(
            "Вторая заметка",
            "Текст второй заметки.",
            0xff9575cd.toInt()
        ),
        Note(
            "Третья заметка",
            "Текст третьей заметки.",
            0xff64b5f6.toInt()
        ),
        Note(
            "Четвертая заметка",
            "Текст четвертой заметки.",
            0xff4db6ac.toInt()
        ),
        Note(
            "Пятая заметка",
            "Текст пятой заметки.",
            0xffb2ff59.toInt()
        ),
        Note(
            "Шестая заметка",
            "Текст шестой заметки.",
            0xffffeb3b.toInt()
        )
    )

    fun getNote(): List<Note> {
        return note
    }
}