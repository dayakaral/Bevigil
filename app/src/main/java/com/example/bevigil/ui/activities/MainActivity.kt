package com.example.bevigil.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bevigil.R
import com.example.bevigil.ui.fragments.AllAssets.AllAssetsFragment
import com.example.bevigil.ui.fragments.ErrorFragment
import com.example.bevigil.ui.fragments.Home.HomeFragment
import com.example.bevigil.utils.Resource
import com.example.bevigil.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnSearchTextListener{

    private lateinit var viewModel: MainViewModel
    final val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)

        val homeFragment = HomeFragment.newInstance(this)
        supportFragmentManager.beginTransaction()
            .add(R.id.main_frame_layout, homeFragment)
            .commit()

        viewModel.progressNavigator = object : ProgressNavigator{
            override fun showProgress(boolean: Boolean) {
                if (boolean){
                    progress_bar.visibility=View.VISIBLE
                }
                else{
                    progress_bar.visibility=View.GONE
                }
            }

        }
        viewModel.onPackageItemClickListener.observe(this, Observer { packageName ->


            if (!viewModel.navigateToDeatail)
                navigateToAllAssetsFragment()
        })

        viewModel.allAssetResponseObserver.observe(this, { response ->


            when(response.status) {

                Status.SUCCESS -> {
                    viewModel.allAssetResponse.postValue(response.data)
                    if (viewModel.navigateToDeatail)
                        navigateToAllAssetsFragment()
                }

                Status.ERROR -> {
                    viewModel.allAssetResponse.postValue(null)
                    navigateToErrorFragment(response.message)
                }
            }

        })
    }
    private fun navigateToErrorFragment(message: String?) {

        val errorFragment = ErrorFragment.newInstance(message?:"","")
        supportFragmentManager.beginTransaction()
            .add(R.id.main_frame_layout, errorFragment)
            .addToBackStack("error")
            .commit()
    }
    private fun navigateToAllAssetsFragment() {
        Log.i(TAG, "navigateToAllAssetsFragment: "+viewModel.allAssetResponse.value.toString())

        val assetsFragment = AllAssetsFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.main_frame_layout, assetsFragment)
            .addToBackStack("assets")
            .commit()
    }


    override fun onSearchedText(searchText: String) {
        Log.i(TAG, "onSearchedText: "+searchText)
        viewModel.navigateToDeatail = false
        viewModel.getAllAsset(searchText)
    }
}