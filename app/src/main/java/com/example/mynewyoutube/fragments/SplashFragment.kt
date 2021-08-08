package com.example.mynewyoutube.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mynewyoutube.R
//import com.example.mynewyoutube.room.AppDataBase
////import com.example.mynewyoutube.room.UserEntity
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.android.gms.common.api.ApiException
//import com.google.firebase.auth.GoogleAuthProvider
//import com.squareup.picasso.Picasso
//import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SplashFragment : Fragment() {
//    lateinit var db: AppDataBase
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
    ): View? {
        val handler = Handler(Looper.getMainLooper())
//        db = AppDataBase.getInstance(requireContext())
        handler.postDelayed({
            findNavController().popBackStack()
//            if (db.dao().get() != null) {
//                findNavController().navigate(R.id.homeFragment)
//            } else {
                findNavController().navigate(R.id.homeFragment)
//            }

        }, 2000)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SplashFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}