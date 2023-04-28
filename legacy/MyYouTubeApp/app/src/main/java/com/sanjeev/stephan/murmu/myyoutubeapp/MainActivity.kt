package com.sanjeev.stephan.murmu.myyoutubeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.sanjeev.stephan.murmu.myyoutubeapp.Config


class MainActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    val config = Config()


    private var youTubeView: YouTubePlayerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        youTubeView = findViewById<View>(R.id.youtube_view) as YouTubePlayerView
        youTubeView!!.initialize(config.YOUTUBE_API_KEY, this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider,
        player: YouTubePlayer,
        wasRestored: Boolean
    ) {
        if (!wasRestored) {
            player.cueVideo("HWz1vyy9Ej0") // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
            //https://www.youtube.com/watch?v=HWz1vyy9Ej0
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider,
        errorReason: YouTubeInitializationResult
    ) {
        if (errorReason.isUserRecoverableError) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show()
        } else {
            val error =
                String.format(getString(R.string.player_error), errorReason.toString())
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent
    ) {
        if (requestCode == RECOVERY_REQUEST) { // Retry initialization if user performed a recovery action
            youTubePlayerProvider!!.initialize(
                config.YOUTUBE_API_KEY,
                this
            )
        }
    }

    protected val youTubePlayerProvider: YouTubePlayer.Provider?
        protected get() = youTubeView

    companion object {
        private const val RECOVERY_REQUEST = 1
    }
}