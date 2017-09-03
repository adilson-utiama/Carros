package br.com.livroandroid.carros.domain

/**
 * Created by Adilson on 03/09/2017.
 */
class Carro {
    var id: Long = 0
    var tipo = ""
    var nome = ""
    var desc = ""
    var urlFoto = ""
    var urlInfo = ""
    var urlVideo = ""
    var latitude = ""
    var longitude = ""

    override fun toString(): String {
        return "Carro(nome='$nome')"
    }
}