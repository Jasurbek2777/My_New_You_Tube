package com.example.mynewyoutube.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.mynewyoutube.R
import com.example.mynewyoutube.activities.DemoActivity
import com.example.mynewyoutube.adapters.VideoRvAdapter
import com.example.mynewyoutube.databinding.FragmentHomeInnerBinding
import com.example.mynewyoutube.databinding.FragmentPlayFragmentBinding
import com.example.mynewyoutube.models.Item
import com.example.mynewyoutube.retrofit.ApiClient
import com.example.mynewyoutube.viewmodels.ViewModelFactory
import com.example.mynewyoutube.viewmodels.YoutubeViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.mobiler.mvvmg18.utils.Status

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PlayFragmentFragment : Fragment() {

    lateinit var binding: FragmentPlayFragmentBinding

    private var param1: String? = null
    private var param2: String? = null

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

        binding = FragmentPlayFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlayFragmentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}