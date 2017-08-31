package br.com.livroandroid.carros.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity

/**
 * Created by Adilson on 30/08/2017.
 */
open class BaseActivity : AppCompatActivity() {

    protected val context: Context get() = this
}