package com.example.plotline_tooltip.ui.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
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
            val tooltipText = "This is a tooltip message"
            showTooltip(button, tooltipText)
            true
//            val tooltipText = "Tooltip Text"
//            val tooltipImageResId = R.drawable.ic_launcher_background
//            tooltipHelper.showTooltip(button, tooltipText, tooltipImageResId)
//            tooltipHandler.postDelayed(tooltipRunnable, 2000) // Hide tooltip after 2 seconds
//            true
        }


        return view
    }

    fun showTooltip(anchorView: View, tooltipText: String) {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val tooltipView = inflater.inflate(R.layout.custom_tooltip_layout, null)

        // Set tooltip text
        val tooltipTextView = tooltipView.findViewById<TextView>(R.id.tooltipTextView)
        tooltipTextView.text = tooltipText

        val popupWindow = PopupWindow(tooltipView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true)

        val windowManager = anchorView.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        // Calculate screen width and height
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels

        // Calculate anchor view position
        val location = IntArray(2)
        anchorView.getLocationOnScreen(location)
        val anchorX = location[0]
        val anchorY = location[1]

        // Set tooltip position relative to anchor view
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, anchorX, anchorY)

        // Calculate tooltip position
        val tooltipWidth = tooltipView.measuredWidth
        val tooltipHeight = tooltipView.measuredHeight
        val tooltipX = anchorX - tooltipWidth / 2 + anchorView.width / 2
        val tooltipY = anchorY - tooltipHeight

        // Adjust tooltip position to keep it within the screen bounds
        val adjustedX = when {
            tooltipX < 0 -> 0
            tooltipX + tooltipWidth > screenWidth -> screenWidth - tooltipWidth
            else -> tooltipX
        }

        val adjustedY = when {
            tooltipY < 0 -> 0
            tooltipY + tooltipHeight > screenHeight -> screenHeight - tooltipHeight
            else -> tooltipY
        }

        // Update tooltip position
        popupWindow.update(adjustedX, adjustedY, -1, -1)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        tooltipHandler.removeCallbacks(tooltipRunnable)
    }
}