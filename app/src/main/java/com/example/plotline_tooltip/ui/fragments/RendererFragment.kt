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

class RendererFragment : Fragment() {

    private lateinit var sharedViewModel: TooltipViewModel
    private lateinit var button: Button
    private lateinit var tooltipHandler: Handler
    private lateinit var tooltipHelper: TooltipHelper
    private lateinit var tooltipRunnable: Runnable
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_renderer, container, false)


        sharedViewModel = ViewModelProvider(requireActivity()).get(TooltipViewModel::class.java)

        button = view.findViewById(R.id.button1)
        tooltipHelper = TooltipHelper(requireContext())
        tooltipHandler = Handler(Looper.getMainLooper())
        tooltipRunnable = Runnable {
            tooltipHelper.hideTooltip()
        }
        button.setOnLongClickListener {
            val tooltipText = "This is a tooltip message"
            showTooltip(button, tooltipText)
            true
        }
        return view
    }

    fun showTooltip(anchorView: View, tooltipText: String) {
        val inflater = anchorView.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val tooltipView = inflater.inflate(R.layout.custom_tooltip_layout, null)

        // Set tooltip text
        val tooltipTextView = tooltipView.findViewById<TextView>(R.id.tooltipTextView)
        tooltipTextView.text = tooltipText

        val popupWindow = PopupWindow(tooltipView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true)

        // Measure tooltip view
        tooltipView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val tooltipWidth = tooltipView.measuredWidth
        val tooltipHeight = tooltipView.measuredHeight

        // Calculate anchor view position
        val location = IntArray(2)
        anchorView.getLocationOnScreen(location)
        val anchorX = location[0]
        val anchorY = location[1]

        // Calculate tooltip position
        val tooltipX = anchorX + anchorView.width / 2 - tooltipWidth / 2
        val tooltipY = anchorY + anchorView.height

        // Set tooltip position relative to anchor view
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, tooltipX, tooltipY)

        // Calculate arrow position
        val arrowView = tooltipView.findViewById<View>(R.id.arrowView)
        val arrowParams = arrowView.layoutParams as ViewGroup.MarginLayoutParams
        arrowParams.leftMargin = tooltipWidth / 2 - arrowView.measuredWidth / 2

        // Update arrow position
        arrowView.layoutParams = arrowParams
    }
    override fun onDestroyView() {
        super.onDestroyView()
        tooltipHandler.removeCallbacks(tooltipRunnable)
    }
}