package com.here.name.subject.soundgarden

import android.Manifest
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.View
import android.widget.Button
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var start: Button
    private lateinit var stop: Button
    private lateinit var play: Button

    private val recorder: MediaRecorder = MediaRecorder()
    private val player: MediaPlayer = MediaPlayer()

    private lateinit var output: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 0)

        start = findViewById(R.id.start)
        stop = findViewById(R.id.stop)
        play = findViewById(R.id.play)

        output = File(filesDir,"record")
        output.createNewFile()

        start.setOnClickListener { view: View? ->
            start.isEnabled = false
            stop.isEnabled = true
            play.isEnabled = false

            recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT)
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
            recorder.setOutputFile(output.path)

            recorder.prepare()
            recorder.start()
        }

        stop.setOnClickListener { view: View? ->
            recorder.stop()
            recorder.reset()

            start.isEnabled = true
            stop.isEnabled = false
            play.isEnabled = true

            //println(output.length())
        }

        play.setOnClickListener { view: View? ->
            /*if (!player.isPlaying) {
                player.setDataSource(output.absolutePath)
                player.prepare()
                player.start()
                val thread = Thread {
                    while (player.isPlaying) {
                    }
                    println("finished playing")
                    player.reset()
                }

                thread.start()

            }*/

            player.setDataSource(output.absolutePath)
            player.prepare()
            player.start()
            player.reset()
        }
    }

}
