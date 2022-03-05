package com.example.bevigil.network

import com.example.bevigil.model.AllAssetsResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface AllAssetService {

    @Headers("X-Access-Token: uyqsbmMOmATlL6bY")
    @GET("api/{package_id}/all-assets/")
    fun getAllAssets(@Path(value = "package_id", encoded = true) packageId: String): Call<JsonObject>
}