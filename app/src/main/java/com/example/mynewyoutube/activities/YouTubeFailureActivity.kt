package com.example.mynewyoutube.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import com.example.mynewyoutube.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.example.mynewyoutube.utils.DeveloperKey

abstract class YouTubeFailureActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    abstract val viewLifecycleOwner: LifecycleOwner
    private  var REQUEST_COD = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_tube_failure)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_COD) {
            getYouTubePlayerProvider()?.initialize(
                DeveloperKey.KEY, this
        )
    }
}

override fun onInitializationFailure(
    p0: YouTubePlayer.Provider?,
    p1: YouTubeInitializationResult?
) {
    if (p1?.isUserRecoverableError == true) {
        p1.getErrorDialog(
            this,
           REQUEST_COD
        ).show()
    } else {
        val errorMessage = String.format("Error player", p1?.toString())
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}

protected abstract fun getYouTubePlayerProvider(): YouTubePlayer.Provider?
}