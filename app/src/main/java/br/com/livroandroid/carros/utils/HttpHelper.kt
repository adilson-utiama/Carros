package br.com.livroandroid.carros.utils

import android.util.Log
import okhttp3.*
import java.io.IOException

/**
 * Created by Adilson on 08/10/2017.
 */

object HttpHelper {
    private val TAG = "http"
    private val LOG_ON = true

    val JSON = MediaType.parse("application/json; charset=utf-8")
    var client = OkHttpClient()

    //GET
    fun get(url: String): String {
        log("httpHelper.get: $url")
        val request = Request.Builder().url(url).get().build()
        return getJson(request)
    }

    //POST com json
    fun post(url: String, json: String): String {
        log("HttpHelper.post: $url")
        val body = RequestBody.create(JSON, json)
        val request = Request.Builder().url(url).post(body).build()
        return getJson(request)
    }

    //POST com parametros (form-urlencoded)
    fun postForm(url: String, params: Map<String, String>): String {
        log("HttpHelper.postForm: $url")
        //Adiciona os parametros chave=valor na request POSt
        val builder = FormBody.Builder()
        for ((key, value) in params) {
            builder.add(key, value)
        }
        val body = builder.build()
        //Faz a request
        val request = Request.Builder().url(url).post(body).build()
        return getJson(request)
    }

    //DELETE
    fun delete(url: String): String {
        log("HttpHelper.delete: $url")
        val request = Request.Builder().url(url).delete().build()
        return getJson(request)
    }

    //Le a resposta do servidor no formato Json
    private fun getJson(request: Request?): String {
        val response = client.newCall(request).execute()
        val responseBody = response.body()
        if (responseBody != null) {
            val json = responseBody.string()
            log("  <<: $json")
            return json
        }
        throw IOException("Erro ao fazer a requisição")
    }

    private fun log(s: String) {
        if (LOG_ON) {
            Log.d(TAG, s)
        }
    }
}