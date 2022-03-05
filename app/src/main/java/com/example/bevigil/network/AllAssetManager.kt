package com.example.bevigil.network

import com.example.bevigil.model.AllAssetsResponse
import com.example.bevigil.model.ErrorData

 interface AllAssetManager {

    fun getAllAssets(packageId: String, callback: AssetCallback<AllAssetsResponse>)


    interface AssetCallback<T> {
        fun onSuccess(success: T)
        fun onFailure(error: ErrorData?)
    }
}