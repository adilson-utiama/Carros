package br.com.livroandroid.carros.domain

import android.content.Context
import android.util.Log
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.extensions.fromJson
import br.com.livroandroid.carros.extensions.getText
import br.com.livroandroid.carros.extensions.getXml
import br.com.livroandroid.carros.extensions.toJson
import br.com.livroandroid.carros.utils.HttpHelper
import org.json.JSONArray
import java.net.URL

/**
 * Created by Adilson on 03/09/2017.
 */
object CarroService {

    private val TAG = "livro"

    private val BASE_URL = "http://livrowebservices.com.br/rest/carros"

    //Busca os carros por tipo (classicos, esportivos ou luxo)
    fun getCarros(context: Context, tipo: TipoCarro): List<Carro> {

        val url = "$BASE_URL/tipo/${tipo.name}"
        Log.d(TAG, url)

        val json = HttpHelper.get(url)
        val carros = fromJson<List<Carro>>(json)

        Log.d(TAG, "${carros.size} carros encontrados")

        return carros
    }

    //Salva um carro
    fun save(carro: Carro): Response {
        //Faz POST do JSON carro
        val json = HttpHelper.post(BASE_URL, carro.toJson())
        val response = fromJson<Response>(json)
        return response
    }

    //Deleta um carro
    fun delete(carro: Carro): Response {
        val url = "$BASE_URL/${carro.id}"
        val json = HttpHelper.delete(url)
        val response = fromJson<Response>(json)
        return response
    }


}