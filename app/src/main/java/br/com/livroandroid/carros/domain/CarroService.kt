package br.com.livroandroid.carros.domain

import android.content.Context
import android.util.Log
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.retrofit.CarrosREST
import br.com.livroandroid.carros.extensions.fromJson
import br.com.livroandroid.carros.extensions.getText
import br.com.livroandroid.carros.extensions.getXml
import br.com.livroandroid.carros.extensions.toJson
import br.com.livroandroid.carros.utils.HttpHelper
import org.json.JSONArray
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

/**
 * Created by Adilson on 03/09/2017.
 */
object CarroService {

    private val TAG = "livro"
    private val BASE_URL = "http://livrowebservices.com.br/rest/carros/"

    private var service: CarrosREST

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(CarrosREST::class.java)
    }

    //Busca os carros por tipo (classicos, esportivos ou luxo)
    fun getCarros(tipo: TipoCarro): List<Carro> {

        val call = service.getCarros(tipo.name)
        val carros = call.execute().body()
        return carros
    }

    //Salva um carro
    fun save(carro: Carro): Response {
        val call = service.save(carro)
        val response = call.execute().body()
        return response
    }

    //Deleta um carro
    fun delete(carro: Carro): Response {
        val call = service.delete(carro.id)
        val response = call.execute().body()
        return response
    }


}