package com.sarindev.exoplayerapp.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.sarindev.exoplayerapp.databinding.ActivityPlayerviewBinding
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.Window
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.google.android.exoplayer2.Player
import com.sarindev.exoplayerapp.R
import com.sarindev.exoplayerapp.viewmodel.PlayerViewModel


class PlayerviewActivity : AppCompatActivity() {

    private val TAG = "PlayerviewActivity"

    var binding: ActivityPlayerviewBinding? = null
    private var player: SimpleExoPlayer? = null
    private var viewModel: PlayerViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        binding = DataBindingUtil.setContentView(this, R.layout.activity_playerview)
        viewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
        binding?.model = viewModel
        binding?.executePendingBindings()
        setObservers()
        viewModel?.initPlaylist()
    }

    private fun startPlayer() {
        player = SimpleExoPlayer.Builder(this).build()
        binding?.playerView?.player = player
        viewModel?.questionList?.let {
            binding?.viewPager2?.adapter = QuestionAdapter(this, it) { index, question ->
                Log.d(TAG, "clicked question - $question")
                playIndex(index)
            }
        }
        addControlVisibilityListener()
        binding!!.viewPager2.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
        }

        setPageTransformer()
        // Add the media items to be played.
        viewModel?.playlist?.forEach {
            player!!.addMediaItem(it)
        }
        // Prepare the player.
        player!!.prepare()
    }

    override fun onResume() {
        super.onResume()
        // Start the playback.
        player?.playWhenReady = true
    }

    override fun onPause() {
        super.onPause()
        player?.playWhenReady = false
    }

    private fun addControlVisibilityListener() {
        binding?.playerView?.setControllerVisibilityListener {
            Log.d(TAG, "visibility = $it, ${View.VISIBLE}")
            when(it){
                View.VISIBLE -> viewModel?.isControlVisible?.set(true)
                else -> viewModel?.isControlVisible?.set(false)
            }
            Log.d(TAG, "isControlVisible = ${viewModel?.isControlVisible?.get()}")
        }
    }

    fun playIndex(index: Int) {
        player?.seekTo(index, 0)
        player?.playWhenReady = true
    }

    private fun setObservers() {
        viewModel?.playlistReady?.observe(this, Observer {
            if (it) {
                startPlayer()
            }
        })
    }

    private fun setPageTransformer() {
        binding!!.viewPager2.setPageTransformer { page, position ->
            val viewPager = page.parent.parent as ViewPager2
            val offset = position * -(2 * 350 + 100)
            if (viewPager.orientation == ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -offset
                } else {
                    page.translationX = offset
                }
            } else {
                page.translationY = offset
            }
        }
    }

}