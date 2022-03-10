package com.example.bevigil.ui.fragments.AllAssets

import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.bevigil.R
import com.example.bevigil.adapters.HomeViewPagerAdapter
import com.example.bevigil.model.AllAssetsResponse
import com.example.bevigil.model.Assets
import com.example.bevigil.ui.activities.MainViewModel
import com.example.bevigil.ui.fragments.AssetFragment.AssetFragment
import com.example.bevigil.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_all_assets.*


@AndroidEntryPoint
class AllAssetsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var fragmentList: ArrayList<Fragment>
    private lateinit var titleList: ArrayList<String>
    var TAG = "AllAssetsFragment"
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
        return inflater.inflate(R.layout.fragment_all_assets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allAssetResponse.observe(viewLifecycleOwner, Observer{ response ->

            if (response == null) {
                return@Observer
            }

            addAllFragmentsToLayout(response)


        })
    }

    private fun addAllFragmentsToLayout(response: AllAssetsResponse?) {

        val assets = response?.assets
        package_name_assets.text = response?.packageId
        titleList = ArrayList()
        fragmentList = ArrayList()

        if (assets?.url != null) {
            titleList.add(Constants.URL)
            fragmentList.add(AssetFragment(assets.url!!))
        }

        if (assets?.CloudFrontURL != null) {
            titleList.add(Constants.CLOUD_FRONT_URL)
            fragmentList.add(AssetFragment(assets.CloudFrontURL!!))
        }

        if (assets?.amazonExecuteApi != null) {
            titleList.add(Constants.AMAZON_EXECUTE_API)
            fragmentList.add(AssetFragment(assets.amazonExecuteApi!!))
        }

        if (assets?.awsUrl != null) {
            titleList.add(Constants.AWS_URL)
            fragmentList.add(AssetFragment(assets.awsUrl!!))
        }

        if (assets?.email != null) {
            titleList.add(Constants.EMAIL)
            fragmentList.add(AssetFragment(assets.email!!))
        }

        if (assets?.filePath != null) {
            titleList.add(Constants.FILE_PATH)
            fragmentList.add(AssetFragment(assets.filePath!!))
        }

        if (assets?.filename != null) {
            titleList.add(Constants.FILE_NAME)
            fragmentList.add(AssetFragment(assets.filename!!))
        }

        if (assets?.firebaseURL != null) {
            titleList.add(Constants.FIREBASE_URL)
            fragmentList.add(AssetFragment(assets.firebaseURL!!))
        }

        if (assets?.host != null) {
            titleList.add(Constants.HOST)
            fragmentList.add(AssetFragment(assets.host!!))
        }

        if (assets?.ipAddressDisclosure != null) {
            titleList.add(Constants.IP_ADDRESS_DISCLOSURE)
            fragmentList.add(AssetFragment(assets.ipAddressDisclosure!!))
        }

        if (assets?.ipUrl != null) {
            titleList.add(Constants.IP_URL)
            fragmentList.add(AssetFragment(assets.ipUrl!!))
        }

        if (assets?.relativeEndPoint != null) {
            titleList.add(Constants.RELATIVE_END_POINT)
            fragmentList.add(AssetFragment(assets.relativeEndPoint!!))
        }

        if (assets?.restApi != null) {
            titleList.add(Constants.REST_API)
            fragmentList.add(AssetFragment(assets.restApi!!))
        }

        homeViewPagerAdapter = HomeViewPagerAdapter(childFragmentManager, fragmentList)
        homeViewPagerAdapter.titlesList = titleList
        homeViewPagerAdapter.size = titleList.size
        asset_pager.adapter = homeViewPagerAdapter
        asset_tab_layout.setupWithViewPager(asset_pager)

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            AllAssetsFragment().apply {

            }
    }
}