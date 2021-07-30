package com.example.autoslideronfragment.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.autoslideronfragment.R
import com.example.autoslideronfragment.databinding.FragmentHomeBinding
import com.malkinfo.sliderviewpager.SliderAdapter
import com.malkinfo.sliderviewpager.SliderItem
import kotlin.math.abs

class HomeFragment : Fragment() {

    lateinit var viewPager: ViewPager2
    private lateinit var sliderItemList: java.util.ArrayList<SliderItem>
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var sliderHandle:Handler
    private lateinit var sliderRun :Runnable


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        viewPager = view.findViewById(R.id.viewPagerImgSlider) as ViewPager2

        sliderItems()
        itemSliderView()
        return view
    }
    private fun sliderItems() {

        sliderItemList = ArrayList()
        sliderAdapter = SliderAdapter(viewPager, sliderItemList)
        viewPager.adapter = sliderAdapter
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val comPosPageTarn = CompositePageTransformer()
        comPosPageTarn.addTransformer(MarginPageTransformer(40))
        comPosPageTarn.addTransformer { page, position ->
            val r: Float = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        viewPager.setPageTransformer(comPosPageTarn)
        sliderHandle = Handler()
        sliderRun = Runnable {
            viewPager.currentItem = viewPager.currentItem + 1
        }

        viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback(){

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandle.removeCallbacks(sliderRun)
                    sliderHandle.postDelayed(sliderRun,2000)
                }
            })

    }

    override fun onPause() {
        super.onPause()
        sliderHandle.removeCallbacks(sliderRun)

    }


    override fun onResume() {
        super.onResume()
        sliderHandle.postDelayed(sliderRun,2000)
    }

    private fun itemSliderView() {
        sliderItemList.add(SliderItem(R.drawable.slid1))
        sliderItemList.add(SliderItem(R.drawable.slid2))
        sliderItemList.add(SliderItem(R.drawable.sli3))
        sliderItemList.add(SliderItem(R.drawable.sli4))
        sliderItemList.add(SliderItem(R.drawable.sli5))
        sliderItemList.add(SliderItem(R.drawable.sli6))
        sliderItemList.add(SliderItem(R.drawable.sli7))
        sliderItemList.add(SliderItem(R.drawable.sli8))
        sliderItemList.add(SliderItem(R.drawable.sli9))
        sliderItemList.add(SliderItem(R.drawable.sli10))
        sliderItemList.add(SliderItem(R.drawable.sli11))
    }
}