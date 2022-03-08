package com.example.bevigil.ui.activities

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bevigil.model.AllAssetsResponse
import com.example.bevigil.model.ErrorData
import com.example.bevigil.network.AllAssetManager
import com.example.bevigil.repository.DataRepository
import com.example.bevigil.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
public class MainViewModel @Inject constructor(private val dataRepository: DataRepository):
    ViewModel() {
    private val allAssetResponseObserver: MutableLiveData<Resource<AllAssetsResponse>> = MutableLiveData()

    private val TAG = "MainViewModelLog"


    init {

    }
    public fun getAllAsset(packageId: String) {
        dataRepository.getAllAssets(packageId, object : AllAssetManager.AssetCallback<AllAssetsResponse> {
            override fun onSuccess(success: AllAssetsResponse) {

                Log.e(TAG, "onSuccess: "+success.assets.toString())
                allAssetResponseObserver.postValue(
                    Resource.success(success)
                )
            }

            override fun onFailure(error: ErrorData) {
                Log.e(TAG, "onFailure: "+error.message)
                allAssetResponseObserver.postValue(
                    Resource.error(error.message?:"Something went wrong")
                )
            }
        })
    }

}