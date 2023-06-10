package com.example.plotline_tooltip.ui.fragments

import DropdownAdapter
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.plotline_tooltip.R
import com.example.plotline_tooltip.databinding.FragmentTooltipEditorBinding
import com.example.plotline_tooltip.ui.tooltip.TooltipHelper
import com.example.plotline_tooltip.ui.viewmodels.TooltipViewModel
import com.example.plotline_tooltip.ui.viewmodels.TooltipViewModelFactory

class TooltipEditorFragment : Fragment() {
    private lateinit var binding: FragmentTooltipEditorBinding
    private lateinit var sharedViewModel: TooltipViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel = ViewModelProvider(requireActivity(), TooltipViewModelFactory(requireContext())).get(TooltipViewModel::class.java)
        binding = FragmentTooltipEditorBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttons = listOf("button1", "button2", "button3", "button4", "button5")
        val adapter = DropdownAdapter(requireContext(), buttons)

        binding.targetElementSpinner.adapter = adapter

        binding.targetElementSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedButton = parent.getItemAtPosition(position).toString()
                Log.d("dev","$selectedButton")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        binding.btnPrintData.setOnClickListener {
            val selectedItem = binding.targetElementSpinner.selectedItem.toString()
            val tooltipText = binding.tooltipTextEditText.text.toString()
            val textSize = binding.textSizeEditText.text.toString()
            val padding = binding.paddingEditText.text.toString()
            val backgroundColor = binding.backgroundColorEditText.text.toString()
            val textColor = binding.textColorEditText.text.toString()
            val cornerRadius = binding.cornerRadiusEditText.text.toString()
            val tooltipWidth = binding.tooltipWidthEditText.text.toString()
            val arrowWidth = binding.arrowWidthEditText.text.toString()
            val arrowHeight = binding.arrowHeightEditText.text.toString()

            println("Selected Item: $selectedItem")
            println("Tooltip Text: $tooltipText")
            println("Text Size: $textSize")
            println("Padding: $padding")
            println("Background Color: $backgroundColor")
            println("Text Color: $textColor")
            println("Corner Radius: $cornerRadius")
            println("Tooltip Width: $tooltipWidth")
            println("Arrow Width: $arrowWidth")
            println("Arrow Height: $arrowHeight")
            showRenderer()
        }
    }

    private fun showRenderer() {
        val fragment = RendererFragment()
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}