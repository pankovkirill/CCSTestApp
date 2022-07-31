package com.example.ccstestapp.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ccstestapp.R
import com.example.ccstestapp.databinding.ActivityMainBinding
import com.example.ccstestapp.view.favorite.FavoriteFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        if (savedInstanceState == null)
            openFragment(MainFragment())

        binding.favorite.setOnClickListener {
            openFragment(FavoriteFragment())
        }

        binding.popular.setOnClickListener {
            openFragment(MainFragment())
        }

    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}