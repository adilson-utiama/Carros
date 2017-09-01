package br.com.livroandroid.carros.activity

import android.os.Bundle
import android.os.PersistableBundle
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.extensions.addFragment
import br.com.livroandroid.carros.extensions.setupToolBar
import br.com.livroandroid.carros.fragments.CarrosFragment

/**
 * Created by Adilson on 31/08/2017.
 */
class CarrosActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_carros)

        //Argumentos : tipo do carro
        val tipo = intent.getSerializableExtra("tipo") as TipoCarro
        val title = getString(tipo.string)

        //Toolbar : configura o titulo e o "Up Navigation"
        setupToolBar(R.id.toolbar, title, true)
        if (savedInstanceState == null) {
            //Adiciona o fragment no layout de marcacao
            //Dentre os argumentos que foram passados para a activity, esta o  tipo de carro
            addFragment(R.id.container, CarrosFragment())
        }
    }
}