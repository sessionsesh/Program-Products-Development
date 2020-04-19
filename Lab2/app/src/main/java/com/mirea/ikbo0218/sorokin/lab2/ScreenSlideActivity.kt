package com.mirea.ikbo0218.sorokin.lab2

import android.os.Bundle
import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2


class ScreenSlideActivity : FragmentActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var fragmentsList: ArrayList<Element>
    private var listSize: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_slide)

        //  Instantiate a ViewPager and a PagerAdapter
        viewPager = findViewById(R.id.viewPager)    //using ViewPager2

        // The pager adapter, which provides the pages to the view pager widget.
        viewPager.adapter = ScreenSlidePagerAdapter(this)

        fragmentsList = intent.getSerializableExtra("serializedArrayList") as ArrayList<Element>
        listSize = fragmentsList.size

        //need to set position of a step in ViewPager
        val position = intent.getIntExtra("position", 0)
        viewPager.currentItem = position    //setting position based on the recyclerView one
    }

//    override fun onBackPressed() {
//        if (viewPager.currentItem == 0) {
//            //  If u currently looking at the first step, allow the system to handle the Back button.
//            //  This calls finis() on this activity and pops the back stack
//            super.onBackPressed()
//        } else {
//            // Otherwise, select the previous step.
//            viewPager.currentItem -= 1
//        }
//
//    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = listSize

        override fun createFragment(position: Int): Fragment {
            return ScreenSlidePageFragment(fragmentsList[position])
        }

    }
}

