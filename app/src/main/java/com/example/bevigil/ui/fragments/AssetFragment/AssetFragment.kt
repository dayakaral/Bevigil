package com.example.bevigil.ui.fragments.AssetFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bevigil.R
import com.example.bevigil.adapters.AssetAdapter
import kotlinx.android.synthetic.main.fragment_asset.*
import kotlinx.android.synthetic.main.fragment_installed_app.*


class AssetFragment(val list: List<String>) : Fragment() {


    lateinit var assetAdapter: AssetAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_asset, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        assetAdapter = AssetAdapter(requireContext())
        assetAdapter.list = this.list
        asset_recycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        asset_recycler.adapter = assetAdapter


    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AssetFragment(ArrayList()).apply {

            }
    }
}