package com.example.bevigil.ui.fragments.AppStore

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bevigil.R
import com.example.bevigil.adapters.InstalledAppsAdapter
import com.example.bevigil.adapters.ItemClickListener
import com.example.bevigil.ui.activities.MainViewModel
import com.example.bevigil.utils.Status
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.fragment_app_store.*
import kotlinx.android.synthetic.main.fragment_installed_app.*


class AppStoreFragment : Fragment(), ItemClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var installedAppsAdapter: InstalledAppsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allAssetResponseObserver.observe(viewLifecycleOwner, Observer {response ->


            when(response.status) {

                Status.SUCCESS -> {
                    viewModel.allAssetResponse.postValue(response.data)

                    addData(response.data?.packageId)
                    appstore_recycler.visibility = View.VISIBLE
                    searching_screen.visibility = View.GONE
                    error_screen.visibility = View.GONE
                }

                Status.ERROR -> {
                    viewModel.allAssetResponse.postValue(null)
                    searching_screen.visibility = View.GONE
                    error_screen.visibility = View.VISIBLE
                    error_message.text = response.message
                    appstore_recycler.visibility = View.INVISIBLE
                }
            }
        })

        installedAppsAdapter = InstalledAppsAdapter(HashMap(), this)
        appstore_recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        appstore_recycler.adapter = installedAppsAdapter
    }

    fun addData(packageName: String?) {
        val list = ArrayList<String?>()
        list.add(packageName)

        installedAppsAdapter.packageNameList = list
        installedAppsAdapter.appNamesList = null
        installedAppsAdapter.notifyDataSetChanged()

    }

    fun onSearchTextChanged(changedText: String) {
//        if (changedText.isEmpty()) {
//
//        }
        if (error_screen.isVisible) {
            error_screen.visibility = View.GONE
            searching_screen.visibility = View.VISIBLE
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            AppStoreFragment().apply {

            }
    }

    override fun onItemClicked(packageName: String) {
        viewModel.onPackageItemClicked(packageName, false)
    }
}