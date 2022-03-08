package com.baykal.edumyclient.data.model.classroom

enum class Lesson(val lessonName: String) {
    math("Mathematics"),
    physics("Physics"),
    chemistry("Chemistry"),
    biyology("Biyology"),
    geography("Geography"),
    history("History"),
    english("English"),
    german("German"),
    other("Other");

    companion object {
        fun get(name: String): Lesson = values().first { it.lessonName == name }
    }
}