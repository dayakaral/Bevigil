package com.example.bevigil.ui.fragments.InstalledApp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bevigil.R
import com.example.bevigil.adapters.InstalledAppsAdapter
import com.example.bevigil.adapters.ItemClickListener
import com.example.bevigil.ui.activities.MainViewModel
import com.example.bevigil.utils.AlogirthmUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_installed_app.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class InstalledAppFragment : Fragment(), ItemClickListener {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel: MainViewModel
    private lateinit var list: HashMap<String, String>
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

        return inflater.inflate(R.layout.fragment_installed_app, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = viewModel.getInstalledApps(context?.packageManager!!)
        Log.i("daya", "onViewCreated: "+list.size+" "+list.toString())
        installedAppsAdapter = InstalledAppsAdapter(list, this)
        installed_recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        installed_recycler.adapter = installedAppsAdapter

    }

    fun onSearchTextChanged(changedText: String) {
        if (changedText.isEmpty()) {
            installedAppsAdapter.appNamesList = list.values.toList()
            installedAppsAdapter.packageNameList = list.keys.toList()
            installedAppsAdapter.notifyDataSetChanged()
            return
        }
        val miniList: HashMap<String, String> = HashMap()
        for (packageNames in this.list.keys.toList()) {
            if (packageNames.isNotEmpty()) {
                if (AlogirthmUtils.newInstance().like(packageNames, changedText)) {
                    miniList[packageNames] = list[packageNames].toString()
                }
            }
        }

        installedAppsAdapter.appNamesList = miniList.values.toList()
        installedAppsAdapter.packageNameList = miniList.keys.toList()
        installedAppsAdapter.notifyDataSetChanged()
    }

    override fun onItemClicked(packageName: String) {
        viewModel.getAllAsset(packageName)
        viewModel.onPackageItemClicked(packageName, true)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InstalledAppFragment().apply {

            }
    }
}