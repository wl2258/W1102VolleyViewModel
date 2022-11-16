package kr.ac.kumoh.s20200607.w1102volleyviewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class SongViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        /**
         * Request Tag 생성
         */
        const val  QUEUE_TAG = "SongVolleyRequest"
    }
    private val list = ArrayList<String>()
    val songs = MutableLiveData<ArrayList<String>>()

    private var queue: RequestQueue
    init {
        songs.value = list
        queue = Volley.newRequestQueue(getApplication())
    }

    fun requestSong() {
        val url = "https://expreesongdb-hoaos.run.goorm.io"

        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            {
                list.clear()
                parseJson(it)
                songs.value = list
                Toast.makeText(getApplication(), it.toString(), Toast.LENGTH_LONG).show()
            },
            {
                Toast.makeText(getApplication(), it.toString(), Toast.LENGTH_LONG).show()
            }
        )

        request.tag = QUEUE_TAG
        queue.add(request)
    }
    //volley -> pascal case

    private fun parseJson(items: JSONArray) {
        for (i in 0 until items.length()) {
            val item: JSONObject = items[i] as JSONObject
            val id = item.getInt("id")
            val title = item.getString("title")
            val singer = item.getString("singer")

            list.add("$title ($singer)")
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue.cancelAll(QUEUE_TAG)
    }



}