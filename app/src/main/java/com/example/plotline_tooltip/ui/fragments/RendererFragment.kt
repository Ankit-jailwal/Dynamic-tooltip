package com.example.plotline_tooltip.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.plotline_tooltip.data.model.TooltipDataEntity
import com.example.plotline_tooltip.databinding.FragmentRendererBinding
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
        val defaultTooltipData = TooltipDataEntity(
            buttonId = "id",
            isVisible = true,
            image = "https://im.indiatimes.in/content/2022/Mar/Article-Body---2022-03-29T111113168_6244494def8d4.jpg",
            text = "Default text",
            textSize = 16,
            padding = 10,
            backgroundColor = "#000000",
            textColor = "#FFFFFF",
            cornerRadius = 20,
            toolTipWidth = 400,
            arrowWidth = 30,
            arrowHeight = 60
        )
        tooltipRunnable = Runnable {
            tooltipHelper.hideTooltip()
        }

        binding.buttonCenter.setOnLongClickListener {
            val tooltipData = getTooltipDataById("button3") ?: defaultTooltipData
            tooltipHelper.showTooltip(binding.buttonCenter, tooltipData)
            true
        }

        binding.buttonLeftBottom.setOnLongClickListener {
            val tooltipData = getTooltipDataById("button4") ?: defaultTooltipData
            tooltipHelper.showTooltip(binding.buttonLeftBottom, tooltipData)
            true
        }

        binding.buttonLeftTop.setOnLongClickListener {
            val tooltipData = getTooltipDataById("button1") ?: defaultTooltipData
            tooltipHelper.showTooltip(binding.buttonLeftTop, tooltipData)
            true
        }

        binding.buttonRightTop.setOnLongClickListener {
            val tooltipData = getTooltipDataById("button2") ?: defaultTooltipData
            tooltipHelper.showTooltip(binding.buttonRightTop, tooltipData)
            true
        }

        binding.buttonRightBottom.setOnLongClickListener {
            val tooltipData = getTooltipDataById("button5") ?: defaultTooltipData
            tooltipHelper.showTooltip(binding.buttonRightBottom, tooltipData)
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tooltipHandler.removeCallbacks(tooltipRunnable)
    }

    private fun getTooltipDataById(id: String) : TooltipDataEntity? {
        var tooltipData: TooltipDataEntity? = null

        sharedViewModel.allTooltipData.observe(viewLifecycleOwner, Observer { data ->
            tooltipData = data.find { item -> item.buttonId == id }
        })

        return tooltipData
    }
}