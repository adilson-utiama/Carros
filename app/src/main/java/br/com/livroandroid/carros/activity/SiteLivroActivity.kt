package br.com.livroandroid.carros.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.activity.dialogs.AboutDialog
import br.com.livroandroid.carros.extensions.setupToolBar
import kotlinx.android.synthetic.main.activity_site_livro.*

class SiteLivroActivity : BaseActivity() {

    private val URL_SOBRE = "http://www.livroandroid.com.br/sobre.htm"
    var webView: WebView? = null
    var progress: ProgressBar? = null
    var swipeToRefresh: SwipeRefreshLayout? = null

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_site_livro)

        //Toolbar
        val actionbar = setupToolBar(R.id.toolbar)
        actionbar.setDisplayHomeAsUpEnabled(true)

        //Views
        webView = findViewById<WebView>(R.id.webview)
        progress = findViewById<ProgressBar>(R.id.progress)

        //Carrega a pagina
        setWebViewClient(webview)
        webView?.loadUrl(URL_SOBRE)

        //Swipe to Refresh
        swipeToRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeTorefresh)
        swipeToRefresh?.setOnRefreshListener {
            webView?.reload()
        }

        //Cores da Animacao
        swipeToRefresh?.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3
        )
    }

    private fun setWebViewClient(webview: WebView?) {
        webview?.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                //Liga o progress
                progress?.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                //Desliga o Progress
                progress?.visibility = View.INVISIBLE

                //Termmina a nimacao do Swipe to Refresh
                swipeToRefresh?.isRefreshing = false
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()
                if (url.endsWith("sobre.htm")) {
                    //Mostra o Dialog
                    AboutDialog.showAbout(supportFragmentManager)
                    //retorna true para informar que interceptamos o evento
                    return true
                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
    }
}
