package br.com.livroandroid.carros.domain.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.livroandroid.carros.domain.Carro

/**
 * Created by Adilson on 08/10/2017.
 */

@Database(entities = arrayOf(Carro::class), version = 1)
abstract class CarrosDatabase : RoomDatabase() {

    abstract fun carroDAO(): CarroDAO

}