package com.codebaron.planetcomics.Utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * @author Anyanwu Nicholas(codeBaron)
 * @since July 25 - 2022
 * @NoteToMeAndViewer The purpose of this file is for adding/implementing function that can be
 * reused across the entire application.
 * @Note functions only and not composable function
 */

/**
 * @param context
 * @return [Boolean]
 * @Note this function handle checking device internet connectivity state
 */
fun isNetworkAvailable(context: Context): Boolean {
    val connectMgr: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectMgr.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}