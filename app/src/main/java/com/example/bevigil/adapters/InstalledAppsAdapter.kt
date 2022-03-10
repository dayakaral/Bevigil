package com.example.bevigil.adapters

import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bevigil.R

class InstalledAppsAdapter(val installedAppList: HashMap<String,String>, val itemClickListener: ItemClickListener): RecyclerView.Adapter<InstalledAppsAdapter.ViewHolder>() {

    var appNamesList:List<String?>? = installedAppList?.values?.toList()
    var packageNameList:List<String?>? = installedAppList?.keys?.toList()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val appNameTv = itemView.findViewById<TextView>(R.id.app_name_tv)
        val packageNameTv = itemView.findViewById<TextView>(R.id.package_name_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.app_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = appNamesList?.get(position)
        val packageName = packageNameList?.get(position)

        holder.appNameTv.text = name
        holder.packageNameTv.text = packageName

        if (name == null) {
            holder.appNameTv.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            if (packageName != null) {
                itemClickListener.onItemClicked(packageName)
            }
        }
    }


    override fun getItemCount(): Int {
        Log.i("daya", "getItemCount: "+packageNameList?.size)
        return packageNameList?.size?:0
    }
}