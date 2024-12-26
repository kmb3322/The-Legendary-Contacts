package com.example.thelegendarycontacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.thelegendarycontacts.ui.DashboardFragment
import com.example.thelegendarycontacts.ui.HomeFragment
import com.example.thelegendarycontacts.ui.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 일반적인 방법으로 레이아웃 세팅
        setContentView(R.layout.activity_main)

        // 첫 화면은 HomeFragment 로
        replaceFragment(HomeFragment())

        // bottomNavigationView를 가져와서 리스너 설정
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.dashboard -> {
                    replaceFragment(DashboardFragment())
                    true
                }
                R.id.notifications -> {
                    replaceFragment(NotificationsFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }
}
