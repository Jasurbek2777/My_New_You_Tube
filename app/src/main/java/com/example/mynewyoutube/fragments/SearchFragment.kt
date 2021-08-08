package com.example.mynewyoutube.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.mynewyoutube.activities.DemoActivity
import com.example.mynewyoutube.adapters.VideoRvAdapter
import com.example.mynewyoutube.databinding.FragmentSearchBinding
import com.example.mynewyoutube.models.Item
import com.example.mynewyoutube.retrofit.ApiClient
import com.example.mynewyoutube.viewmodels.SearchViewModel
import com.example.mynewyoutube.viewmodels.SearchViewModelFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.mobiler.mvvmg18.utils.Status

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var videoViewModel: SearchViewModel
    lateinit var adapter: VideoRvAdapter
    var list = ArrayList<Item>()
    lateinit var binding: FragmentSearchBinding
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
        binding = FragmentSearchBinding.inflate(inflater, container, false)

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

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                videoViewModel = ViewModelProviders.of(
                    this@SearchFragment,
                    SearchViewModelFactory(ApiClient.apiService,text.toString())
                )[SearchViewModel::class.java]
                videoViewModel.getData().observe(viewLifecycleOwner, {
                    when (it.status) {
                        Status.ERROR -> {
                            adapter.submitList(arrayListOf())
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                            binding.progress.visibility = View.INVISIBLE
                        }
                        Status.LOADING -> {
                            adapter.submitList(arrayListOf())
                            binding.progress.visibility = View.VISIBLE

                        }
                        Status.SUCCESS -> {
                            binding.progress.visibility = View.INVISIBLE
                            val items = it.data?.items
                            list = items as ArrayList<Item>
                            adapter.submitList(list)
                            binding.rv.adapter = adapter
                        }
                    }
                })

                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                videoViewModel = ViewModelProviders.of(
                    this@SearchFragment,
                    SearchViewModelFactory(ApiClient.apiService,text.toString())
                )[SearchViewModel::class.java]
                videoViewModel.getData().observe(viewLifecycleOwner, {
                    when (it.status) {
                        Status.ERROR -> {
                            adapter.submitList(arrayListOf())
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                            binding.progress.visibility = View.INVISIBLE
                        }
                        Status.LOADING -> {
                            adapter.submitList(arrayListOf())
                            binding.progress.visibility = View.VISIBLE

                        }
                        Status.SUCCESS -> {
                            binding.progress.visibility = View.INVISIBLE
                            val items = it.data?.items
                            list = items as ArrayList<Item>
                            adapter.submitList(list)
                            binding.rv.adapter = adapter
                        }
                    }
                })
                return true
            }
        })


        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}