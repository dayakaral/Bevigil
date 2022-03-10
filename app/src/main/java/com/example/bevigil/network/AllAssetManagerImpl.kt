package com.example.bevigil.network

import android.util.Log
import com.example.bevigil.model.AllAssetsResponse
import com.example.bevigil.model.ErrorData
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class AllAssetManagerImpl @Inject constructor(private val assetService: AllAssetService)
    :AllAssetManager {
    override fun getAllAssets(
        packageId: String,
        callback: AllAssetManager.AssetCallback<AllAssetsResponse>
    ) {
        var call:Call<JsonObject> = assetService.getAllAssets(packageId)
        requestAPI(call, callback)
    }


    fun requestAPI(call: Call<JsonObject>, callback: AllAssetManager.AssetCallback<AllAssetsResponse>) {
        call.clone().enqueue(getCustomCallBack(callback))
    }

    fun getCustomCallBack(callback: AllAssetManager.AssetCallback<AllAssetsResponse>):Callback<JsonObject> {
        return object: Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code() != 200) {
                    callback.onFailure(ErrorData(response.message()))
                    return
                }
                val jsonObject = JSONObject(response.body().toString())
                if (response.body()?.get("detail") != null) {

                    callback.onFailure(ErrorData(response.body()?.get("detail")?.asString))
                } else if (response.isSuccessful){

                    callback.onSuccess(
                        getDataFromJSONObject(jsonObject, AllAssetsResponse::class.java) as AllAssetsResponse
                    )
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                callback.onFailure(ErrorData(t.message))
            }
        }
    }

    /*
     * method to parse json object and store data to respective class
     * //@param jsonObject
     * //@param classOfT
     * //@param <T>
     * @return Object of <T>
     */
    fun <T> getDataFromJSONObject(jsonObject: JSONObject?, classOfT: Class<T>?): Any? {
        return try {
            val gson = Gson()
            gson.fromJson(jsonObject?.toString(), classOfT)
        } catch (e: Exception) {
            Log.e("TAG", "getDataFromJSONObject: " + e.message)
            null
        } catch (er: OutOfMemoryError) {
            null
        }
    }
}