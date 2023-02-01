package com.baubap.android.fakelog.framework.common

import android.app.Activity
import android.app.AlertDialog
import android.content.Context

// Gets some screen component dimensions
fun getScreenComponentHeight(context: Context, name: String): Int {
    var result = 0
    val resourceId: Int = context.resources.getIdentifier(name, "dimen", "android")
    if (resourceId > 0) {
        result = context.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

// Pre builds an alert dialog
fun showAlertMessage(context: Context?, title: String?, message: String?, onBtnOkPress: () -> Unit = {}) {
    var alertDialog: AlertDialog?
    context?.let {
        val builder = AlertDialog.Builder(it)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.cancel()
            onBtnOkPress()
        }
        alertDialog = builder.create()
        alertDialog?.let { dialog ->
            if ((it as? Activity)?.isFinishing == false && !dialog.isShowing)
                alertDialog?.show()
        }
    }
}