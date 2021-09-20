package com.sarindev.exoplayerapp.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.MediaItem
import com.sarindev.exoplayerapp.model.Question

class PlayerViewModel: ViewModel() {

    private var _playlistReady = MutableLiveData<Boolean>()
    val playlistReady = _playlistReady

    val playlist = ArrayList<MediaItem>()
    val questionList = ObservableArrayList<Question>()
    var isControlVisible = ObservableBoolean()

    fun initPlaylist(){
        val firstVideoUri =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
        val secondVideoUri = "https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4"
        // Build the media items.
        val item0: MediaItem = MediaItem.fromUri(firstVideoUri)
        val item1: MediaItem = MediaItem.fromUri(secondVideoUri)
        val item2: MediaItem = MediaItem.fromUri(firstVideoUri)
        val item3: MediaItem = MediaItem.fromUri(secondVideoUri)
        val item4: MediaItem = MediaItem.fromUri(firstVideoUri)
        playlist.add(item0)
        playlist.add(item1)
        playlist.add(item2)
        playlist.add(item3)
        playlist.add(item4)

        initQuestions()
        playlistReady.value = true
    }

    private fun initQuestions(){
        questionList.add(Question(0,"video text 0"))
        questionList.add(Question(1,"video text 1"))
        questionList.add(Question(2,"video text 2"))
        questionList.add(Question(3,"video text 3"))
        questionList.add(Question(4,"video text 4"))
    }
}