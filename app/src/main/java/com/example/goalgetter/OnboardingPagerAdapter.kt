package com.example.goalgetter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class OnboardingPagerAdapter(private val context: Context) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(getLayout(position), container, false)
        container.addView(view)

        when (position) {
            0 -> {
                val btnNext = view.findViewById<Button>(R.id.btnNext)
                btnNext.setOnClickListener {
                    // Navigate to the next onboarding screen (position 1)
                    (context as Activity).findViewById<ViewPager>(R.id.viewPager).currentItem = 1
                }

                val btnSkip = view.findViewById<TextView>(R.id.btnSkip)
                btnSkip.setOnClickListener {
                    // Navigate to the main activity
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    (context as Activity).finish()
                }
            }
            1 -> {
                val btnNext2 = view.findViewById<Button>(R.id.btnNext2)
                btnNext2.setOnClickListener {
                    // Navigate to the main activity
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    (context as Activity).finish()
                }
            }
        }

        return view
    }

    override fun getCount(): Int = 2 // Number of onboarding pages

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    private fun getLayout(position: Int): Int {
        return when (position) {
            0 -> R.layout.onboarding_page_1
            1 -> R.layout.onboarding_page_2
            else -> R.layout.onboarding_page_1
        }
    }
}