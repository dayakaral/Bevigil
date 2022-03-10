package com.example.bevigil.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.bevigil.ui.fragments.AppStore.AppStoreFragment
import com.example.bevigil.ui.fragments.InstalledApp.InstalledAppFragment

class HomeViewPagerAdapter(fm: FragmentManager, val fragmentList: List<Fragment>) : FragmentStatePagerAdapter(fm){

    var size:Int = 0
    var titlesList: List<String>?= null
    override fun getCount(): Int {
        return size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title = titlesList?.get(position)
        title = title?.replace("_"," ")
        return convertSTring(title?:"")
    }

    fun convertSTring(message: String) :String{
        val charArray: CharArray = message.toCharArray()
        var foundSpace = true

        for (i in charArray.indices) {

            // if the array element is a letter
            if (Character.isLetter(charArray[i])) {

                // check space is present before the letter
                if (foundSpace) {

                    // change the letter into uppercase
                    charArray[i] = Character.toUpperCase(charArray[i])
                    foundSpace = false
                }
            } else {
                // if the new character is not character
                foundSpace = true
            }
        }

        // convert the char array to the string

        // convert the char array to the string
        return String(charArray)
    }
}