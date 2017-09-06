package br.com.livroandroid.carros.domain

import android.content.Context
import android.util.Log
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.extensions.fromJson
import br.com.livroandroid.carros.extensions.getText
import br.com.livroandroid.carros.extensions.getXml
import org.json.JSONArray

/**
 * Created by Adilson on 03/09/2017.
 */
object CarroService {

    private val TAG = "livro"

    //Busca os carros por tipo (classicos, esportivos ou luxo)
    fun getCarros(context: Context, tipo: TipoCarro): List<Carro> {
        //O Arquivo que temos que ler
        val raw = getArquivoRawJson(tipo)

        //Abre o arquivo para leitura
        val resources = context.resources
        val inputStream = resources.openRawResource(raw)
        inputStream.bufferedReader().use {
            //Le o xml e cria a lista de carros
            //val xml = it.readText()
            //val carros = parseXML(xml)

            //Le o json e cria a lista de carros
            val json = it.readText()

            //Converte o Json para List<Carro>
            val carros = fromJson<List<Carro>>(json)
            return carros
        }
    }

    fun parserJSON(json: String): List<Carro> {
        val carros = mutableListOf<Carro>()

        //Cria um array com este JSON
        val array = JSONArray(json)

        //Percorre cada carro (json)
        for (i in 0..array.length() - 1) {
            //JSON do carro
            val jsonCarro = array.getJSONObject(i)
            val carro = Carro()

            //Le as informacoes de cadda carro
            carro.nome = jsonCarro.optString("nome")
            carro.desc = jsonCarro.optString("desc")
            carro.urlFoto = jsonCarro.optString("url_foto")
            carros.add(carro)
        }
        Log.d(TAG, "${carros.size} carros encontrados")
        return carros
    }

    //Retorna o arquivo que temos que ler para o tipo informado
    fun getArquivoRaw(tipo: TipoCarro) = when (tipo) {
        TipoCarro.classicos -> R.raw.carros_classicos
        TipoCarro.esportivos -> R.raw.carros_esportivos
        else -> R.raw.carros_luxo
    }

    fun getArquivoRawJson(tipo: TipoCarro) = when (tipo) {
        TipoCarro.classicos -> R.raw.carros_classicos_json
        TipoCarro.esportivos -> R.raw.carros_esportivos_json
        else -> R.raw.carros_luxo_json
    }

    fun parseXML(xmlString: String): List<Carro> {
        val carros = mutableListOf<Carro>()
        val xml = xmlString.getXml()

        //Le todas as tags <carro>
        val nodeCarros = xml.getChildren("carro")

        //Insere cada carro na Lista
        for (node in nodeCarros) {
            val carro = Carro()

            //Le as informacoes de cada carro
            carro.nome = node.getText("nome")
            carro.desc = node.getText("desc")
            carro.urlFoto = node.getText("url_foto")
            carros.add(carro)
        }

        Log.d(TAG, "${carros.size} carros encontrados")
        return carros
    }

}