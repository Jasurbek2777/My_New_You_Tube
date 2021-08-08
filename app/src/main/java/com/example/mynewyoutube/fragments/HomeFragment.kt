package com.example.mynewyoutube.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynewyoutube.R
import com.example.mynewyoutube.adapters.ViewPagerAdapter
import com.example.mynewyoutube.databinding.AddBottomshetDialogBinding
import com.example.mynewyoutube.databinding.FragmentHomeBinding
import com.example.mynewyoutube.databinding.ShareDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var viewPagerAdapter: ViewPagerAdapter
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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewPagerAdapter = ViewPagerAdapter(requireActivity(), 1)
        binding.notification.setOnClickListener {
            findNavController().navigate(R.id.notificationFragment)
        }
        binding.search.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }
        binding.share.setOnClickListener {
            var dialog =
                ShareDialogBinding.inflate(LayoutInflater.from(requireContext()), container, false)
            var builder = AlertDialog.Builder(requireContext())
            builder.setView(dialog.root)

            dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            builder.show()
        }
        binding.viewPager.adapter = viewPagerAdapter
        binding.navView.setOnNavigationItemSelectedListener { item ->

            when (item.title) {
                "Home" -> {
                    findNavController().navigate(R.id.homeFragment)

                }
                "Navigation" -> {
                    findNavController().navigate(R.id.navigationFragment)

                }
                "Add" -> {
                    val bsh = AddBottomshetDialogBinding.inflate(
                        LayoutInflater.from(requireContext()),
                        null,
                        false
                    )
                    val builder = BottomSheetDialog(requireContext())
                    builder.setContentView(bsh.root)
                    bsh.close.setOnClickListener {
                        builder.dismiss()
                    }
                    builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    builder.show()

                }
                "Follows" -> {
                    findNavController().navigate(R.id.navigationFragment)

                }
                "Library" -> {
                    findNavController().navigate(R.id.navigationFragment)

                }
                else -> {
                    Toast.makeText(requireContext(), "No view", Toast.LENGTH_SHORT).show()
                }
            }

            false
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}