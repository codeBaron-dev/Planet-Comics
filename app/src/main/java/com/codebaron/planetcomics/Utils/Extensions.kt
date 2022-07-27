package com.codebaron.planetcomics.Utils

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

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

/**
 * @param context
 * @param msg
 * @param lent
 * @Note this function handles displaying warning toast messages to the user
 */
fun errorToast(context: Context, msg: String, lent: Int) {
    Toast.makeText(context, "$msg \uD83D\uDE12", lent).show()
}

/**
 * @param context
 * @param msg
 * @param lent
 * @Note this function handles displaying success toast messages to the user
 */
fun successToast(context: FragmentActivity?, msg: String, lent: Int) {
    Toast.makeText(context, "$msg \uD83D\uDE00", lent).show()
}

/**
 * @param context
 * @param messageType
 * @param fmt
 * @param data
 * @Note this function handles displaying any required message in a dialog to the user
 */
fun showMessageDialog(
    context: FragmentActivity?,
    messageType: String,
    fmt: String?,
    vararg data: Any?
) {
    val msg = String.format(fmt!!, *data)
    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
    alertDialog.setTitle(messageType)
    alertDialog.setMessage(msg)
    alertDialog.setCancelable(true)
    alertDialog.show()
}