package br.com.livroandroid.carros.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.CarroService
import br.com.livroandroid.carros.extensions.loadUrl
import br.com.livroandroid.carros.extensions.setupToolBar
import br.com.livroandroid.carros.extensions.toast
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.activity_carro_contents.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

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
                alert(R.string.msg_confirma_excluir_carro, R.string.app_name) {
                    positiveButton(R.string.sim) {
                        //Confirmou o excluir
                        taskExcluir()
                    }
                    negativeButton(R.string.nao) {
                        //Nao confirmou
                    }
                }.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //Exclui um carro do servidor
    fun taskExcluir() {
        doAsync {
            val response = CarroService.delete(carro)
            uiThread {
                toast(response.msg)
                finish()
            }
        }
    }
}
