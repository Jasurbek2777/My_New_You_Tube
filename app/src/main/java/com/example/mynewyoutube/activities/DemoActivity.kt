package com.example.mynewyoutube.activities

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import com.example.mynewyoutube.R
import com.example.mynewyoutube.adapters.VideoRvAdapter
import com.example.mynewyoutube.databinding.FragmentPlayFragmentBinding
import com.example.mynewyoutube.models.Item
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.example.mynewyoutube.utils.DeveloperKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DemoActivity : YouTubeFailureActivity() {
    private lateinit var binding1: FragmentPlayFragmentBinding
    lateinit var item: ArrayList<Item>
    lateinit var item1: Item
    lateinit var adapter: VideoRvAdapter

    lateinit var id: String
    override val viewLifecycleOwner: LifecycleOwner
        get() = TODO("Not yet implemented")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1 = FragmentPlayFragmentBinding.inflate(layoutInflater)
        setContentView(binding1.root)
        item = ArrayList()
        adapter = VideoRvAdapter(object : VideoRvAdapter.itemClick {
            override fun itemOnClick(data: Item, position: Int) {
                Toast.makeText(this@DemoActivity, "Clicked", Toast.LENGTH_SHORT).show()
            }
        })
        val data = intent.getStringExtra("item")
        val pos = intent.getIntExtra("pos", 0)
        val gson = Gson()

        val type = object : TypeToken<List<Item>>() {}.type
        item = gson.fromJson(data, type)
        item1 = item[pos]
        adapter.submitList(item)
        binding1.rv.adapter = adapter
        binding1.progress.visibility = View.INVISIBLE
        binding1.title.text = item1.snippet.title
        val youTubePlayerFragment =
            fragmentManager.findFragmentById(R.id.youtube_fragment) as YouTubePlayerFragment
        binding1.follow.setOnClickListener {
            binding1.follow.setTextColor(Color.DKGRAY)
        }
        youTubePlayerFragment.initialize(DeveloperKey.KEY, this)
    }

    override fun getYouTubePlayerProvider(): YouTubePlayer.Provider? {
        return fragmentManager.findFragmentById(R.id.playFragmentFragment) as YouTubePlayerFragment
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        if (p2) {
            p1?.cueVideo(item1.id.videoId)
        }
    }
}