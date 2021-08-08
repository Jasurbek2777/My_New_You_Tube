package com.example.mynewyoutube.fragments


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mynewyoutube.R
import com.example.mynewyoutube.databinding.FragmentSignBinding
import com.example.mynewyoutube.room.AppDataBase
import com.example.mynewyoutube.room.UserEntity
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var   mAuth:FirebaseAuth

class SignFragment : Fragment() {
    lateinit var db: AppDataBase
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding:FragmentSignBinding
    lateinit var currentUser: UserEntity
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        mAuth = FirebaseAuth.getInstance();
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSignBinding.inflate(inflater, container, false)
        db = AppDataBase.getInstance(requireContext())
//        signIn()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
            var currentUser = mAuth?.currentUser;
            updateUI(currentUser);


        return binding.root
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        currentUser?.uid?.let { firebaseAuthWithGoogle(it) }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()
            ) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = mAuth.currentUser
                    updateUI(user)
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)

                    updateUI(null)
                }

            }
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

//    private fun signIn() {
//        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//        var googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
//        val signInIntent = googleSignInClient.signInIntent
//        startActivityForResult(signInIntent, 1)
//    }
//
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 1) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = task.getResult(ApiException::class.java)!!
//                firebaseAuthWithGoogle(account.idToken!!)
//            } catch (e: ApiException) {
//                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(requireActivity()
//            ) { task ->
//                if (task.isSuccessful) {
//                    val user: FirebaseUser? = auth.currentUser
//
//                } else {
//                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
//
//                }
//            }
//    }
//
//    @SuppressLint("SimpleDateFormat")
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(requireActivity()) { task ->
//                if (task.isSuccessful) {
//                    val account1 = auth.currentUser
//                    val date = Date()
//                    val dateFormat = SimpleDateFormat("HH:mm")
//                    val curremtDate = dateFormat.format(date)
//                    if (account1 != null) {
//                        currentUser = UserEntity(
//                            account1?.displayName,
//                            account1?.photoUrl.toString(),
//                            account1?.email,
//                            account1.uid,
//                            curremtDate,
//                            online = true,
//                            signed = true
//                        )
//                    }
//                    db.dao().add(currentUser)
//                    db.dao().get()
//                    findNavController().popBackStack()
//                    findNavController().navigate(R.id.homeFragment)
//                } else {
//                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
//
//
//                }
//            }
//    }
}