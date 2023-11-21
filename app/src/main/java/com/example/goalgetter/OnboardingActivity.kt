package com.example.goalgetter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager

class OnboardingActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val adapter = OnboardingPagerAdapter(this)
        viewPager.adapter = adapter
    }
}