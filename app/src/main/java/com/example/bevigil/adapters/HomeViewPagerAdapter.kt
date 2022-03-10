package com.example.bevigil.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.bevigil.ui.fragments.AppStore.AppStoreFragment
import com.example.bevigil.ui.fragments.InstalledApp.InstalledAppFragment

class HomeViewPagerAdapter(fm: FragmentManager, val fragmentList: List<Fragment>) : FragmentStatePagerAdapter(fm){

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) "Installed" else "App Store"
    }
}