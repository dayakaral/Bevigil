package com.example.bevigil.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bevigil.R
import com.example.bevigil.ui.fragments.Home.HomeFragment
import com.example.bevigil.utils.Resource
import com.example.bevigil.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    final val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)

        val homeFragment = HomeFragment.newInstance("","")
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame_layout, homeFragment)
            .commit()

        viewModel.onPackageItemClickListener.observe(this, Observer { packageName ->


            viewModel.getAllAsset(packageName)
        })

        viewModel.allAssetResponseObserver.observe(this, { response ->


            when(response.status) {

                Status.SUCCESS -> {
                    viewModel.allAssetResponse.postValue(response.data)
                    navigateToAllAssetsFragment()
                }

                Status.ERROR -> {
                    viewModel.allAssetResponse.postValue(null)
                    Toast.makeText(this@MainActivity, response.message, Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun navigateToAllAssetsFragment() {
        Log.i(TAG, "navigateToAllAssetsFragment: "+viewModel.allAssetResponse.value.toString())
    }
}