package br.com.livroandroid.carros.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentManager
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.fragments.CarrosFragment
import br.com.livroandroid.carros.fragments.FavoritosFragment

/**
 * Created by Adilson on 03/09/2017.
 */
class TabsAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    //Quantidade de Tabs
    override fun getCount(): Int {
        return 4
    }

    //Retorna o tipo pela posicao
    fun getTipoCarro(position: Int) = when (position) {
        0 -> TipoCarro.classicos
        1 -> TipoCarro.esportivos
        2 -> TipoCarro.luxo
        else -> TipoCarro.favoritos
    }

    //Titulo da Tab
    override fun getPageTitle(position: Int): CharSequence {
        val tipo = getTipoCarro(position)
        return context.getString(tipo.string)
    }

    //Fragment que vai mostrar a lista de carros
    override fun getItem(position: Int): Fragment {
        if (position == 3) {
            //Favoritos
            return FavoritosFragment()
        }
        val tipo = getTipoCarro(position)
        val frag: Fragment = CarrosFragment()
        frag.arguments = Bundle()
        frag.arguments.putSerializable("tipo", tipo)
        return frag
    }


}
