package br.com.livroandroid.carros.extensions

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by Adilson on 03/09/2017.
 */

//Mostra um Toast
fun Fragment.toast(message: CharSequence, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, message, length).show()
}

fun Fragment.toast(@StringRes message: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, message, length).show()
}
