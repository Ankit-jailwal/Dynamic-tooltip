package com.example.plotline_tooltip.ui.fragments

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.plotline_tooltip.R
import com.example.plotline_tooltip.data.model.TooltipData
import com.example.plotline_tooltip.databinding.FragmentRendererBinding
import com.example.plotline_tooltip.databinding.FragmentTooltipEditorBinding
import com.example.plotline_tooltip.ui.tooltip.TooltipHelper
import com.example.plotline_tooltip.ui.viewmodels.TooltipViewModel
import com.example.plotline_tooltip.ui.viewmodels.TooltipViewModelFactory

class RendererFragment : Fragment() {

    private lateinit var sharedViewModel: TooltipViewModel
    private lateinit var tooltipHandler: Handler
    private lateinit var tooltipHelper: TooltipHelper
    private lateinit var tooltipRunnable: Runnable
    private lateinit var binding: FragmentRendererBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel = ViewModelProvider(requireActivity(), TooltipViewModelFactory(requireContext())).get(TooltipViewModel::class.java)
        binding = FragmentRendererBinding.inflate(inflater, container, false)
        tooltipHelper = TooltipHelper(requireContext())
        tooltipHandler = Handler(Looper.getMainLooper())

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tooltipRunnable = Runnable {
            tooltipHelper.hideTooltip()
        }

        val tooltipData = TooltipData(
            buttonId = "button1",
            isVisible = true,
            text = "Sample tooltip text",
            textSize = 30,
            padding = 20,
            backgroundColor = "#FF4081", // Unique color: pink
            textColor = "#FFF405", // White text color
            cornerRadius = 3,
            toolTipWidth = 200,
            arrowWidth = 60,
            arrowHeight = 50
        )

        binding.buttonCenter.setOnLongClickListener {
            val tooltipText = "This is a tooltip message"
            tooltipHelper.showTooltip(binding.buttonCenter, tooltipText, null)
            true
        }

        binding.buttonLeftBottom.setOnLongClickListener {
            val tooltipText = "This is a tooltip message"
            tooltipHelper.showTooltip(binding.buttonLeftBottom, tooltipText, null)
            true
        }

        binding.buttonLeftTop.setOnLongClickListener {
            val tooltipText = "This is a tooltip message"
            tooltipHelper.showTooltip(binding.buttonLeftTop, tooltipText, null)
            true
        }

        binding.buttonRightTop.setOnLongClickListener {
            val tooltipText = "This is a tooltip message"
            tooltipHelper.showTooltip(binding.buttonRightTop, tooltipText, null)
            true
        }

        binding.buttonRightBottom.setOnLongClickListener {
            val tooltipText = "This is a tooltip message"
            tooltipHelper.showTooltip(binding.buttonRightBottom, tooltipText, null)
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tooltipHandler.removeCallbacks(tooltipRunnable)
    }
}