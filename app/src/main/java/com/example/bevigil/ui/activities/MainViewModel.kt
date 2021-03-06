package com.example.bevigil.ui.activities

import android.content.pm.PackageManager
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
    val allAssetResponseObserver: MutableLiveData<Resource<AllAssetsResponse>> = MutableLiveData()
    val onPackageItemClickListener: MutableLiveData<String> = MutableLiveData()
    var progressNavigator: ProgressNavigator?=null

    val allAssetResponse: MutableLiveData<AllAssetsResponse?> = MutableLiveData()
    var navigateToDeatail: Boolean = false

    private val TAG = "MainViewModelLog"


    init {

    }
    public fun getAllAsset(packageId: String) {
        progressNavigator?.showProgress(true)
        dataRepository.getAllAssets(packageId, object : AllAssetManager.AssetCallback<AllAssetsResponse> {
            override fun onSuccess(success: AllAssetsResponse) {

                progressNavigator?.showProgress(false)
                Log.e(TAG, "onSuccess: "+success.assets.toString())
                allAssetResponseObserver.postValue(
                    Resource.success(success)
                )
            }

            override fun onFailure(error: ErrorData) {
                progressNavigator?.showProgress(false)
                Log.e(TAG, "onFailure: "+error.message)
                allAssetResponseObserver.postValue(
                    Resource.error(error.message?:"Something went wrong")
                )
            }
        })
    }

    fun onPackageItemClicked(packageId: String, navigateToFlow: Boolean) {
        navigateToDeatail = navigateToFlow
        onPackageItemClickListener.postValue(packageId)
    }

    fun getInstalledApps(packageManager: PackageManager): HashMap<String, String> {
        return dataRepository.getInstalledApps(packageManager)
    }

}