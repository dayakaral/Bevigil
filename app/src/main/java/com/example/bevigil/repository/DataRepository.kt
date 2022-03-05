package com.example.bevigil.repository

import com.example.bevigil.model.AllAssetsResponse
import com.example.bevigil.network.AllAssetManager
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val allAssetManager: AllAssetManager
) {

    fun getAllAssets(packageId: String, assetCallback: AllAssetManager.AssetCallback<AllAssetsResponse>)
    = allAssetManager.getAllAssets(packageId, assetCallback)
}