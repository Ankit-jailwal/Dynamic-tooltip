package com.example.plotline_tooltip.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.plotline_tooltip.R
import com.example.plotline_tooltip.ui.tooltip.TooltipHelper
import com.example.plotline_tooltip.ui.viewmodels.TooltipViewModel

class TooltipEditorFragment : Fragment() {

    private lateinit var sharedViewModel: TooltipViewModel
    private lateinit var button: Button
    private lateinit var tooltipHelper: TooltipHelper
    private lateinit var tooltipHandler: Handler
    private lateinit var tooltipRunnable: Runnable
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tooltip_editor, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(TooltipViewModel::class.java)

        button = view.findViewById(R.id.btnPrintData)
        tooltipHelper = TooltipHelper(requireContext())
        tooltipHandler = Handler(Looper.getMainLooper())
        tooltipRunnable = Runnable {
            tooltipHelper.hideTooltip()
        }
        button.setOnLongClickListener {
            val tooltipText = "Tooltip Text"
            val tooltipImageResId = R.drawable.ic_launcher_background
            tooltipHelper.showTooltip(button, tooltipText, tooltipImageResId)
            tooltipHandler.postDelayed(tooltipRunnable, 2000) // Hide tooltip after 2 seconds
            true
            true
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tooltipHandler.removeCallbacks(tooltipRunnable)
    }
}