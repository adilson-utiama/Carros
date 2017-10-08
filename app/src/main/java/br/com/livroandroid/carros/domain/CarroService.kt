package br.com.livroandroid.carros.domain

import android.content.Context
import android.util.Log
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.extensions.fromJson
import br.com.livroandroid.carros.extensions.getText
import br.com.livroandroid.carros.extensions.getXml
import org.json.JSONArray
import java.net.URL

/**
 * Created by Adilson on 03/09/2017.
 */
object CarroService {

    private val TAG = "livro"

    //Busca os carros por tipo (classicos, esportivos ou luxo)
    fun getCarros(context: Context, tipo: TipoCarro): List<Carro> {

        val url = "http://livrowebservices.com.br/rest/carros/tipo/${tipo.name}"

        Log.d(TAG, url)

        val json = URL(url).readText()
        val carros = fromJson<List<Carro>>(json)

        Log.d(TAG, "${carros.size} carros encontrados")

        return carros
    }


}