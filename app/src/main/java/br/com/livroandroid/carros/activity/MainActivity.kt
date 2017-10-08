package br.com.livroandroid.carros.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.adapter.TabsAdapter
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.extensions.setupToolBar
import br.com.livroandroid.carros.extensions.toast
import br.com.livroandroid.carros.utils.Prefs
import kotlinx.android.synthetic.main.activity_main.*

import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_main)

        setupToolBar(R.id.toolbar)
        setupNavDrawer()
        setupViewPagerTabs()

        //FAB: Floating Action button (variavel fab foi gerada pelo Kotlin Extensions)
        fab.setOnClickListener() {
            //            val snack = Snackbar.make(it, "Clicou no botao FAB!", Snackbar.LENGTH_LONG)
//            snack.show()
            startActivity<CarroFormActivity>()
        }
    }

    //Configura o Navigation Drawer
    private fun setupNavDrawer() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

    }

    //Configura o ViewPager e os tabs
    private fun setupViewPagerTabs() {
        //Configura o viewPager + tabs
        //As variaveis viewPager e tabLayout sao geradas automaticamente pelo Kotlin Extensions
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = TabsAdapter(context, supportFragmentManager)

        //Crai as Tabs com o mesmo adapter utilizado pelo ViewPager
        tabLayout.setupWithViewPager(viewPager)

        //Cor branca no texto ( O fundo e definido no layout)
        val cor = ContextCompat.getColor(context, R.color.white)
        tabLayout.setTabTextColors(cor, cor)

        //Salva e recupera a ultima Tab acessada
        val tabIdx = Prefs.getInt("tabIdx")
        Log.d("livro", tabIdx.toString())
        viewPager.currentItem = tabIdx
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(page: Int) {
                //Salva o indice da pagina/tab selecionada
                Prefs.tabIdx = page
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_carros_todos -> {
                toast("Clicou em carros")
            }
            R.id.nav_item_carros_classicos -> {
                //Utilizando a biblioteca Anko
                startActivity<CarrosActivity>("tipo" to TipoCarro.classicos)
            }
            R.id.nav_item_carros_esportivos -> {
//                val intent = Intent(context, CarrosActivity::class.java)
//                intent.putExtra("tipo", TipoCarro.esportivos)
//                startActivity(intent)
                startActivity<CarrosActivity>("tipo" to TipoCarro.esportivos)
            }
            R.id.nav_item_carros_luxo -> {
                startActivity<CarrosActivity>("tipo" to TipoCarro.luxo)
            }
            R.id.nav_item_site_livro -> {
                startActivity<SiteLivroActivity>()
            }
            R.id.nav_item_settings -> {
                toast("Clicou em Configurações")
            }
        }
        //Fecha o menu depos de tratar o evento
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
