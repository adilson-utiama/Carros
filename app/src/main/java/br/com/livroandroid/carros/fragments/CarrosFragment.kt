package br.com.livroandroid.carros.fragments

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.activity.CarroActivity
import br.com.livroandroid.carros.adapter.CarroAdapter
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.CarroService
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.extensions.toast
import br.com.livroandroid.carros.utils.AndroidUtils
import kotlinx.android.synthetic.main.fragment_carros.*
import org.jetbrains.anko.doAsync

import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread


class CarrosFragment : BaseFragment() {

    private var tipo: TipoCarro = TipoCarro.classicos
    private var carros = listOf<Carro>()

    //Usando Androoid Extensions//var recyclerView: RecyclerView? = null

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        //Le o parametro tipo enviado (Classicos, Esportivos, Luxo)
        tipo = arguments.getSerializable("tipo") as TipoCarro
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //retorna a view /res/layout/fragment_carros.xml
        val view = inflater?.inflate(R.layout.fragment_carros, container, false)

        return view

    }

    override fun onViewCreated(view: View?, icicle: Bundle?) {
        super.onViewCreated(view, icicle)

        //Views
        //Usando Android Extensions//recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()
        taskCarros()
    }

    fun taskCarros() {

        val internetOK = AndroidUtils.isNetworkAvaiable(context)

        if (internetOK) {
            //Abre uma Thread
            doAsync {
                //Busca os carros
                carros = CarroService.getCarros(tipo)

                //Atualiza a lista na UI Thread
                uiThread {
                    //Atualiza a lista
                    recyclerView.adapter = CarroAdapter(carros, { onClickCarro(it) })
                }
            }
        } else {
            toast("Não esta conectado na internet!")
        }

    }

    fun onClickCarro(carro: Carro) {
        activity.startActivity<CarroActivity>("carro" to carro)
    }

}
