package br.com.livroandroid.carros.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by Adilson on 08/10/2017.
 */

object AndroidUtils {

    fun isNetworkAvaiable(context: Context): Boolean {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networks = connectivity.allNetworks

//        for(n in networks) {
//            val info = connectivity.getNetworkInfo(n)
//            if(info.state == NetworkInfo.State.CONNECTED) {
//                return true
//            }
//        }
//        return false

        return networks
                .map { connectivity.getNetworkInfo(it) }
                .any { it.state == NetworkInfo.State.CONNECTED }
    }
}