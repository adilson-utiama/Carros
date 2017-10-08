package br.com.livroandroid.carros.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.extensions.loadUrl
import br.com.livroandroid.carros.extensions.setupToolBar
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.activity_carro_contents.*
import org.jetbrains.anko.startActivity

class CarroActivity : BaseActivity() {

    //Lazy loading: evita objetos nulos, nao sendo necessario operador ?
    //Lança uma exceçao, caso o objeto seja null
    val carro: Carro by lazy { intent.getParcelableExtra<Carro>("carro") }

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
        appBarImg.loadUrl(carro.urlFoto)
    }

    //Adiciona as opcoes Salvar e Deletar no Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_carro, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_editar -> {
                startActivity<CarroFormActivity>("carro" to carro)
                finish()
            }
            R.id.action_deletar -> {
                TODO("deletar o carro")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
