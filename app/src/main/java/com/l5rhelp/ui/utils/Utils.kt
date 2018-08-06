package com.l5rhelp.ui.utils

import android.content.Context
import android.net.ConnectivityManager

class Utils {

    companion object {
        fun hasNetworkConnection(context: Context?): Boolean {

            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }

}