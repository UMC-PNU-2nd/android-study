package com.example.flo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {

    //소괄호 : 클래스를 다른 클래스로 상속을 진행할 때는 소괄호를 넣어줘야 한다.

    //전역 변수
    lateinit var binding : ActivitySongBinding

    lateinit var song : Song
    lateinit var timer : Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSong()
        setPlayer(song)

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
    override fun onDestroy(){
        super.onDestroy()
        timer.interrupt()
    }

    override fun getRequestedOrientation(): Int {
        return super.getRequestedOrientation()
    }
    private fun initSong(){
        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            song = Song (
                intent.getStringExtra("title")!!,
                intent.getStringExtra("singer")!!,
                intent.getIntExtra("second", 0),
                intent.getIntExtra("playTime", 0),
                intent.getBooleanExtra("isPlaying", false)
            )
        }
        starTimer()
    }

    private fun setPlayer(song: Song){
        binding.songMusicTitleTv.text = intent.getStringExtra("title")
        binding.songSingerNameTv.text = intent.getStringExtra("singer")
        binding.songStartTimeTv.text = String.format("%02d:%02d", song.second/60, song.second%60)
        binding.songEndTimeTv.text = String.format("%02d:%02d", song.playTime/60, song.playTime%60)
        binding.songProgressSb.progress = (song.second*1000/song.playTime)

        setPlayerStatus1(song.isPlaying)

    }
    fun setPlayerStatus1 (isPlaying : Boolean){
        //song.isPlaying = isPlaying
        timer.isPlaying = isPlaying
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
    private fun starTimer(){
        timer = Timer(song.playTime, song.isPlaying)
        timer.start()
    }
    inner class Timer(private val playTime : Int, var isPlaying: Boolean = true):Thread(){
        private var second : Int = 0
        private var mills : Float = 0f


        override fun run() {
            super.run()
            while(true){

                if (second >= playTime){
                    break
                }
                if (isPlaying){
                    sleep(50)
                    mills += 50

                    runOnUiThread(){
                        binding.songProgressSb.progress =((mills / playTime)*100).toInt()
                    }
                    if (mills % 1000 == 0f){
                        runOnUiThread{
                            binding.songStartTimeTv.text = String.format("%02d:%02d", second/60, second%60)

                        }
                        second++
                    }
                }
            }
        }
    }
}