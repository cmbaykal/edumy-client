package com.baykal.edumyclient.base.extension

import java.text.SimpleDateFormat
import java.util.*

inline val Date.toBirth: String
    get() {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return dateFormat.format(this)
    }

inline val Date.string: String
    get() {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())
        return dateFormat.format(this)
    }

inline val Date.stringWithoutTime: String
    get() {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return dateFormat.format(this)
    }

inline val Date.toJson: String
    get() {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
        return dateFormat.format(this)
    }