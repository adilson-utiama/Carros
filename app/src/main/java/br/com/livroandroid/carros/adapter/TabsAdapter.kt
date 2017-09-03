package br.com.livroandroid.carros.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentManager
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.fragments.CarrosFragment

/**
 * Created by Adilson on 03/09/2017.
 */
class TabsAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    //Quantidade de Tabs
    override fun getCount(): Int {
        return 3
    }

    //Retorna o tipo pela posicao
    fun getTipoCarro(position: Int) = when (position) {
        0 -> TipoCarro.classicos
        1 -> TipoCarro.esportivos
        else -> TipoCarro.luxo
    }

    //Titulo da Tab
    override fun getPageTitle(position: Int): CharSequence {
        val tipo = getTipoCarro(position)
        return context.getString(tipo.string)
    }

    //Fragment que vai mostrar a lista de carros
    override fun getItem(position: Int): Fragment {
        val tipo = getTipoCarro(position)
        val frag: Fragment = CarrosFragment()
        frag.arguments = Bundle()
        frag.arguments.putSerializable("tipo", tipo)
        return frag
    }


}
