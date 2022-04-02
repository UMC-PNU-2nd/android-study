package com.example.flo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {

    //소괄호 : 클래스를 다른 클래스로 상속을 진행할 때는 소괄호를 넣어줘야 한다.

    //전역 변수
    lateinit var binding : ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            binding.songMusicTitleTv.text = intent.getStringExtra("title")
            binding.songSingerNameTv.text = intent.getStringExtra("singer")
        }

        binding.songDownIb.setOnClickListener {
            finish()
        }

        binding.songMiniplayerIv.setOnClickListener {
            setPlayerStatus1(false)
        }

        binding.songPauseIv.setOnClickListener {
            setPlayerStatus1(true)
        }
        binding.songLikeIv.setOnClickListener {
            setPlayerStatus2(false)
        }

        binding.songLikeOnIv.setOnClickListener {
            setPlayerStatus2(true)
        }
        binding.songUnlikeIv.setOnClickListener {
            setPlayerStatus3(false)
        }

        binding.songUnlikeOnIv.setOnClickListener {
            setPlayerStatus3(true)
        }


    }

    fun setPlayerStatus1 (isPlaying : Boolean){
        if(isPlaying){
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
        } else {
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
        }
    }
    fun setPlayerStatus2 (isPlaying : Boolean){
        if(isPlaying){
            binding.songLikeIv.visibility = View.VISIBLE
            binding.songLikeOnIv.visibility = View.GONE
        } else {
            binding.songLikeIv.visibility = View.GONE
            binding.songLikeOnIv.visibility = View.VISIBLE
        }
    }
    fun setPlayerStatus3 (isPlaying : Boolean){
        if(isPlaying){
            binding.songUnlikeIv.visibility = View.VISIBLE
            binding.songUnlikeOnIv.visibility = View.GONE
        } else {
            binding.songUnlikeIv.visibility = View.GONE
            binding.songUnlikeOnIv.visibility = View.VISIBLE
        }
    }
}