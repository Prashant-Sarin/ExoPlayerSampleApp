package com.sarindev.exoplayerapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sarindev.exoplayerapp.R
import com.sarindev.exoplayerapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.btnStyledPlayer?.setOnClickListener {
            startActivity(Intent(this, SimplePlayerActivity::class.java))
        }
        binding?.btnPlayerView?.setOnClickListener {
            startActivity(Intent(this, PlayerviewActivity::class.java))
        }
    }

}