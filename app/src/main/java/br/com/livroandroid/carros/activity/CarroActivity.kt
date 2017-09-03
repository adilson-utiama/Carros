package br.com.livroandroid.carros.activity

import android.os.Bundle
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.extensions.loadUrl
import br.com.livroandroid.carros.extensions.setupToolBar
import kotlinx.android.synthetic.main.activity_carro_contents.*

class CarroActivity : BaseActivity() {

    var carro: Carro? = null

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_carro)

        //Le o carro enviado como parametro
        carro = intent.getSerializableExtra("carro") as Carro

        //Seta o nome do carro como titulo da toolbar
        setupToolBar(R.id.toolbar, carro?.nome, true)

        //Atualiza os dados do carro na tela
        initViews()
    }

    fun initViews() {
        //Variaveis geradas automaticamente pelo Kotlin Extensions (Veja Import)
        tDesc.text = carro?.desc
        img.loadUrl(carro?.urlFoto)
    }
}
