package com.luisseia.calendar_clock_app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.luisseia.calendar_clock_app.databinding.FragmentHomeFullscreenBinding
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone


class HomeFullscreenFragment : Fragment() {
    private val hideHandler = Handler(Looper.myLooper()!!)


    private var _binding: FragmentHomeFullscreenBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeFullscreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)


    }

    override fun onPause() {
        super.onPause()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)


        activity?.window?.decorView?.systemUiVisibility = 0

    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onStart() {
        super.onStart()
        val calendar = Calendar.getInstance()

        val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
        val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        binding.textDate.text = "$dayOfWeek $month $dayOfMonth"

        val currentLocale = Locale.getDefault()
        val country = currentLocale.country
        val timeZone = TimeZone.getDefault()
        val timeZoneID = timeZone.id

        binding.textZone.text = "$country, $timeZoneID"
    }

}