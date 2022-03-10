package com.example.bevigil.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bevigil.R
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat


class AssetAdapter(val context: Context): RecyclerView.Adapter<AssetAdapter.ViewHolder>() {


     var list: List<String>? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AssetAdapter.ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.asset_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AssetAdapter.ViewHolder, position: Int) {

        holder.sno.text = "${position+1}. "
        holder.url.text = list?.get(position)

        holder.url.setOnClickListener {
            if (URLUtil.isValidUrl(holder.url.text.toString())) {

                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(holder.url.text.toString())
                startActivity(i)
            }
        }
    }

    private fun startActivity(i: Intent) {
        context.startActivity(i)
    }

    override fun getItemCount(): Int {
        return list?.size?:0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sno = itemView.findViewById<TextView>(R.id.sno)
        val url = itemView.findViewById<TextView>(R.id.url)
    }
}