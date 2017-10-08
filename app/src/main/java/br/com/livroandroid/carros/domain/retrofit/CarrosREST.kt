package br.com.livroandroid.carros.domain.retrofit

import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.Response
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Adilson on 08/10/2017.
 */
interface CarrosREST {

    @GET("tipo/{tipo}")
    fun getCarros(@Path("tipo") tipo: String): Call<List<Carro>>

    @POST("./")
    fun save(@Body carro: Carro): Call<Response>

    @DELETE("{id}")
    fun delete(@Path("id") id: Long): Call<Response>
}