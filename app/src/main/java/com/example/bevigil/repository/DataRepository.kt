package com.example.bevigil.repository

import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import com.example.bevigil.model.AllAssetsResponse
import com.example.bevigil.network.AllAssetManager
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val allAssetManager: AllAssetManager
) {
    private val installedApps:HashMap<String,String> = HashMap()
    fun getAllAssets(packageId: String, assetCallback: AllAssetManager.AssetCallback<AllAssetsResponse>)
    = allAssetManager.getAllAssets(packageId, assetCallback)

    fun getInstalledApps(packageManager: PackageManager) : HashMap<String,String> {
        if (installedApps.isNotEmpty())
            return installedApps
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        for (resolvedInfo in packageManager.queryIntentActivities(intent, 0)) {
            val appName = resolvedInfo?.activityInfo?.loadLabel(packageManager)?:""
            val packageName = resolvedInfo?.activityInfo?.packageName?:""
            Log.i("daya", "getInstalledApps: "+appName+" "+packageName)
            installedApps[packageName] = appName.toString()

        }
        return installedApps
    }
}