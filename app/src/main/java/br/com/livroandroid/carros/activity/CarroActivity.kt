package br.com.livroandroid.carros.activity

import android.os.Bundle
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.extensions.loadUrl
import br.com.livroandroid.carros.extensions.setupToolBar
import kotlinx.android.synthetic.main.activity_carro_contents.*

class CarroActivity : BaseActivity() {

    //Lazy loading: evita objetos nulos, nao sendo necessario operador ?
    //Lança uma exceçao, caso o objeto seja null
    val carro by lazy { intent.getSerializableExtra("carro") as Carro }

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_carro)

        //Seta o nome do carro como titulo da toolbar
        setupToolBar(R.id.toolbar, carro.nome, true)

        //Atualiza os dados do carro na tela
        initViews()
    }

    fun initViews() {
        //Variaveis geradas automaticamente pelo Kotlin Extensions (Veja Import)
        tDesc.text = carro.desc
        img.loadUrl(carro.urlFoto)
    }
}
