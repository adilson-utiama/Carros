package br.com.livroandroid.carros.domain

import android.arch.persistence.room.Database
import br.com.livroandroid.carros.domain.dao.DatabaseManager

/**
 * Created by Adilson on 08/10/2017.
 */
object FavoritosService {

    //Retorna todos os carros favoritados
    fun getCarros(): List<Carro> {
        val dao = DatabaseManager.getCarroDAO()
        val carros = dao.findAll()
        return carros
    }

    //Verifica se um carro esta favoritado
    fun isFavorito(carro: Carro): Boolean {
        val dao = DatabaseManager.getCarroDAO()
        val exists = dao.getById(carro.id) != null
        return exists
    }

    //Salva o carro ou deleta
    fun favoritar(carro: Carro): Boolean {
        val dao = DatabaseManager.getCarroDAO()
        val favorito = isFavorito(carro)
        if (favorito) {
            //Remove dos Favoritos
            dao.delete(carro)
            return false
        }
        //ADiciona aos Favoritos
        dao.insert(carro)
        return true
    }
}