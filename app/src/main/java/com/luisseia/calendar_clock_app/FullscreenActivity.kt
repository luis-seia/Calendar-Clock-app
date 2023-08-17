package com.luisseia.calendar_clock_app

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.luisseia.calendar_clock_app.databinding.ActivityFullscreenBinding

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullscreenBinding
    private lateinit var fullscreenContent: TextView
    private lateinit var fullscreenContentControls: LinearLayout
    private val hideHandler = Handler(Looper.myLooper()!!)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else{
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        val bateryReceviver : BroadcastReceiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                if( intent != null){
                    val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0 )
                    Toast.makeText(applicationContext, level.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
        registerReceiver(bateryReceviver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        // as soon as the application opens the first fragment should
        // be shown to the user in this case it is algorithm fragment
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFullscreenFragment()).commit()
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener {
        // By using switch we can easily get the
        // selected fragment by using there id
        lateinit var selectedFragment: Fragment
        when (it.itemId) {
            R.id.home -> {
                selectedFragment = HomeFullscreenFragment()
            }
            R.id.alarm -> {
                selectedFragment = AlarmFullscreenFragment()
            }
            R.id.stopwatch -> {
                selectedFragment = StopwatchFullscreenFragment()
            }
            R.id.timer ->{
                selectedFragment = TimerFullscreenFragment()
            }
        }
        // It will help to replace the
        // one fragment to other.
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
        true
    }
}