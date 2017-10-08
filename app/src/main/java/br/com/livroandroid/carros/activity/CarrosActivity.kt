package br.com.livroandroid.carros.activity

import android.os.Bundle
import android.view.MenuItem
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.CarroService
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.extensions.addFragment
import br.com.livroandroid.carros.extensions.setupToolBar
import br.com.livroandroid.carros.extensions.toast
import br.com.livroandroid.carros.fragments.CarrosFragment
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread


/**
 * Created by Adilson on 31/08/2017.
 */
class CarrosActivity : BaseActivity() {

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        setContentView(R.layout.activity_carros)

        //Argumentos : tipo do carro
        val tipo = intent.getSerializableExtra("tipo") as TipoCarro
        val title = getString(tipo.string)

        //Toolbar : configura o titulo e o "Up Navigation"
        setupToolBar(R.id.toolbar, title, true)
        if (icicle == null) {
            //Adiciona o fragment no layout de marcacao
            //Dentre os argumentos que foram passados para a activity, esta o  tipo de carro
            addFragment(R.id.container, CarrosFragment())
        }


    }


}