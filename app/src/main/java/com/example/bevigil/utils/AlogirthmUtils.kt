package com.example.bevigil.utils

import android.util.Log
import com.example.bevigil.ui.fragments.InstalledApp.InstalledAppFragment
import java.util.*

class AlogirthmUtils {


    companion object {
        @JvmStatic
        fun newInstance() = AlogirthmUtils()

    }
    fun like(str: String, expr: String): Boolean {
        var str = str
        var expr = expr
        expr = expr.lowercase(Locale.getDefault()) // ignoring locale for now
        expr = expr.replace(".", "\\.") // "\\" is escaped to "\" (thanks, Alan M)
        // ... escape any other potentially problematic characters here
        expr = expr.replace("?", ".")
        expr = expr.replace("%", ".*")
        expr = ".*$expr.*"
        str = str.lowercase(Locale.getDefault())
        Log.i("AlogithmUtils", "like: " + str + " " + expr + " " + str.matches(Regex(expr)))
        return str.matches(Regex(expr))
    }


}