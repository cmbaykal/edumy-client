package com.baykal.edumyclient.data.model.classroom

enum class Lesson(val lessonName: String) {
    turkish("Türk Dili ve Edebiyatı"),
    math("Matematik"),
    physics("Fizik"),
    chemistry("Kimya"),
    biyology("Biyoloji"),
    geography("Coğrafya"),
    history("Tarih"),
    english("İngilizce"),
    german("Almanca"),
    other("Diğer");

    companion object {
        fun get(name: String): Lesson = values().first { it.lessonName == name }
    }
}