package br.com.livroandroid.carros.domain

/**
 * Created by Adilson on 08/10/2017.
 */
data class Response(val id: Long, val status: String, val msg: String, val url: String) {
    fun isOK() = "OK".equals(status, ignoreCase = true)
}