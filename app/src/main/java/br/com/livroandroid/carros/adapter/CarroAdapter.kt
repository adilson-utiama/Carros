package br.com.livroandroid.carros.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.extensions.loadUrl
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.adapter_carro.view.*

/**
 * Created by Adilson on 03/09/2017.
 */

// define oconstrutor que recebe (carros, onclick)
class CarroAdapter(
        val carros: List<Carro>,
        val onClick: (Carro) -> Unit
) : RecyclerView.Adapter<CarroAdapter.CarrosViewHolder>() {


    // retorna a quantidade de carros na lista
    override fun getItemCount() = this.carros.size

    //Inlfa o layout do adapter e retorna o ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CarrosViewHolder {
        //infla a view do adapter
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.adapter_carro, parent, false)

        //Retorn o ViewHolder que contem todas as views
        val holder = CarrosViewHolder(view)

        return holder
    }

    //Faz o bind para atualizar o valor das views com os dados do carro
    override fun onBindViewHolder(holder: CarrosViewHolder, position: Int) {

        //Recupera o objeto Carro
        val carro = carros[position]

        //Declara a variavel view para facilitar o acesso abaixo
        //A view contem as variaveis definidas no XML (Lembre-se do nomde de cada id)
        val view = holder.itemView

        //Funcao with()
        //aceita um objeto como parametro, executa uma expressao entre chaves,
        //permite acessar variaveis sem usar o objeto

        with(view) {
            //Atualiza os dados do carro
            tNome.text = carro.nome

            //Faz o download da foto e mostra o ProgressBAr
            img.loadUrl(carro.urlFoto, view.progress)

            //Adiciona o evento de clique na linha
            setOnClickListener { onClick(carro) }
        }

    }


    //ViewHolder com as views
    class CarrosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //usando Android Extensions
//        var tNome: TextView
//        var img: ImageView
//        var progress: ProgressBar
//        var cardView: CardView
//
//        init {
//            //Salva as views no ViewHolder
//            tNome = view.findViewById<TextView>(R.id.tNome)
//            img = view.findViewById<ImageView>(R.id.img)
//            progress = view.findViewById<ProgressBar>(R.id.progress)
//            cardView = view.findViewById<CardView>(R.id.card_view)
//        }
    }
}