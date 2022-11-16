package kr.ac.kumoh.s20200607.w1102volleyviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kr.ac.kumoh.s20200607.w1102volleyviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: SongViewModel
    private var songs: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[SongViewModel::class.java] // this: context
        /**
         * [songViewModel] 해시맵으로 생각함
         * get...Model 대신 사용
         */
        model.requestSong()

        model.songs.observe(this, Observer<ArrayList<String>> {
            songs = model.songs.value?.toTypedArray()
            binding.listSongs.adapter = ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, songs as Array<out String>)
        })

    }
}