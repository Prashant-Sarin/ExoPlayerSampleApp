package com.sarindev.exoplayerapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.sarindev.exoplayerapp.R
import com.sarindev.exoplayerapp.databinding.ActivitySimplePlayerBinding

class SimplePlayerActivity : AppCompatActivity() {

    private val TAG = "SimplePlayerActivity"

    var binding: ActivitySimplePlayerBinding? = null
    var player: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_simple_player)
        setExoPlayer()
    }

    private fun setExoPlayer() {
        player = SimpleExoPlayer.Builder(this).build()
        binding?.playerView?.player = player

        val videoUri =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
        // Build the media item.
        val mediaItem: MediaItem = MediaItem.fromUri(videoUri)

        // Set the media item to be played.
        player?.setMediaItem(mediaItem)
        // Prepare the player.
        player?.prepare()
//        // Start the playback.
//        player.play()
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
}