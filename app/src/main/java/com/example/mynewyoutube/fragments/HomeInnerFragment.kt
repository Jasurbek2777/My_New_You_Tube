package com.example.mynewyoutube.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.mynewyoutube.activities.DemoActivity
import com.example.mynewyoutube.adapters.VideoRvAdapter
import com.example.mynewyoutube.databinding.FragmentHomeInnerBinding
import com.example.mynewyoutube.models.Item
import com.example.mynewyoutube.retrofit.ApiClient
import com.example.mynewyoutube.viewmodels.ViewModelFactory
import com.example.mynewyoutube.viewmodels.YoutubeViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.mobiler.mvvmg18.utils.Status

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeInnerFragment : Fragment() {
    lateinit var videoViewModel: YoutubeViewModel
    lateinit var binding: FragmentHomeInnerBinding
    lateinit var adapter: VideoRvAdapter
    private var param1: String? = null
    private var param2: String? = null
    var list = ArrayList<Item>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeInnerBinding.inflate(inflater, container, false)
        adapter = VideoRvAdapter(object : VideoRvAdapter.itemClick {
            override fun itemOnClick(data: Item, position: Int) {
                val intent = Intent(requireContext(), DemoActivity::class.java)

                val gson = Gson()
                var type = object : TypeToken<List<Item>>() {}.type
                val s = gson.toJson(list, type)
                intent.putExtra("item", s)
                intent.putExtra("pos", position)
                startActivity(intent)
            }
        })
        videoViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiClient.apiService)
        )[YoutubeViewModel::class.java]
        videoViewModel.getYoutubeLiveData().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progress.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    binding.progress.visibility = View.INVISIBLE
                    val items = it.data?.items
                    adapter.submitList(items)
                    list = items as ArrayList<Item>

                }
            }
        })
        binding.rv.adapter = adapter

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeInnerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}