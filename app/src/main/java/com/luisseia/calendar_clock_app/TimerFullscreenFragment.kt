package com.luisseia.calendar_clock_app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.luisseia.calendar_clock_app.databinding.FragmentTimerFullscreenBinding

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class TimerFullscreenFragment : Fragment() {
    private val hideHandler = Handler(Looper.myLooper()!!)

    private lateinit var hourPicker: NumberPicker
    private lateinit var minutePicker: NumberPicker
    private lateinit var secondPicker: NumberPicker


    fun setTime(hour: Int, minute: Int, second: Int) {
        hourPicker.value = hour
        minutePicker.value = minute
        secondPicker.value = second
    }

    fun getHour(): Int {
        return hourPicker.value
    }

    fun getMinute(): Int {
        return minutePicker.value
    }

    fun getSecond(): Int {
        return secondPicker.value
    }

    private var _binding: FragmentTimerFullscreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTimerFullscreenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.hourPicker.minValue = 0
        binding.hourPicker.maxValue = 23

        binding.minutePicker.minValue = 0
        binding.minutePicker.maxValue = 59

        binding.secondPicker.minValue = 0
        binding.secondPicker.maxValue = 59
    }
}