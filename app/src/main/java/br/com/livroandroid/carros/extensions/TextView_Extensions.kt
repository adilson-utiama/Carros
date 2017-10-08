package br.com.livroandroid.carros.extensions

import android.widget.TextView

/**
 * Created by Adilson on 08/10/2017.
 */

var TextView.string: String
    get() = text.toString()
    set(value) {
        text = value
    }

fun TextView.isEmpty() = text.trim().isEmpty()