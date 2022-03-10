package com.example.bevigil.ui.fragments.Home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.bevigil.R
import com.example.bevigil.adapters.HomeViewPagerAdapter
import com.example.bevigil.adapters.InstalledAppsAdapter
import com.example.bevigil.databinding.FragmentHomeBinding
import com.example.bevigil.ui.fragments.AppStore.AppStoreFragment
import com.example.bevigil.ui.fragments.InstalledApp.InstalledAppFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_installed_app.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var installedAppFragment: InstalledAppFragment
    private lateinit var appStoreFragment: AppStoreFragment

    private val binding get() = homeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fragmentList:ArrayList<Fragment> = ArrayList()
        installedAppFragment = InstalledAppFragment.newInstance("","")
        appStoreFragment = AppStoreFragment.newInstance("","")
        fragmentList.add(installedAppFragment)
        fragmentList.add(appStoreFragment)

        text_input_layout?.addTextChangedListener(object :TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                installedAppFragment.onSearchTextChanged(p0.toString())
            }
        })

        search_button.setOnClickListener {

        }

        homeViewPagerAdapter = HomeViewPagerAdapter(childFragmentManager, fragmentList)

        viewPager = binding.homePager
        viewPager.adapter = homeViewPagerAdapter
        home_tab_layout.setupWithViewPager(viewPager)


    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}