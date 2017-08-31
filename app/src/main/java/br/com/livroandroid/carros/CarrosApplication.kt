package br.com.livroandroid.carros

import android.app.Application
import android.util.Log

/**
 * Created by Adilson on 30/08/2017.
 */
class CarrosApplication : Application() {

    private val TAG = "CarrosApplication"

    //Chamado quando o Android criar o processo da aplicacao
    override fun onCreate() {
        super.onCreate()
        //Salva a instancia para termos acesso como Singleton
        appInstance = this
    }

    companion object {
        //Singleton da classe Application
        private var appInstance: CarrosApplication? = null

        fun getInstance(): CarrosApplication {
            if(appInstance == null) {
                throw IllegalStateException("Configure a classe de Aplication no AndroidManifest")
            }
            return appInstance!!
        }

    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "CarrosApplication.onTerminated()")
    }

}