package com.example.flo


//제목, 가수, 사진,재생시간,현재 재생시간, isplaying(재생 되고 있는지)

data class Song(
        val title : String = "",
        val singer : String = "",
        val second : Int = 0,
        val playTime : Int = 0,
        val isPlaying : Boolean = false
)
