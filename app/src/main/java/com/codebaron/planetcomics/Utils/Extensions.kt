package com.codebaron.planetcomics.Utils

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.codebaron.planetcomics.R
import com.codebaron.planetcomics.models.ComicDTO
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso

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

/**
 * this function handles displaying the comic details in a bottomSheet
 * @param data
 */
fun openBottomSheet(data: ComicDTO?, context: Context) {
    val bottomSheet = BottomSheetDialog(context)
    val layout = LayoutInflater.from(context).inflate(R.layout.comics_bottom_sheet, null)

    Picasso.get().load(data?.img).into(layout.findViewById<ImageView>(R.id.comic_image))
    layout.findViewById<TextView>(R.id.comic_title).text = data?.title
    layout.findViewById<TextView>(R.id.comic_description).text = data?.alt
    layout.findViewById<TextView>(R.id.comic_publish_date).text =
        "Released date: ${data?.day}-${data?.month}-${data?.year}"
    layout.findViewById<Button>(R.id.dismiss_btn)
        .setOnClickListener { bottomSheet.dismiss() }
    bottomSheet.setContentView(layout)
    bottomSheet.show()
}