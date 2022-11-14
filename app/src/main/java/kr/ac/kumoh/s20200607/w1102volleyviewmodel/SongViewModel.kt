package kr.ac.kumoh.s20200607.w1102volleyviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SongViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        const val  QUEUE_TAG = "SongVolleyRequest"
    }
    private val list = ArrayList<String>()
    val songs = MutableLiveData<ArrayList<String>>()

    
}