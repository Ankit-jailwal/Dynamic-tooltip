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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.plotline_tooltip.R
import com.example.plotline_tooltip.data.model.TooltipDataEntity
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
            val tooltipText = binding.tooltipTextEditText.text.toString()?.takeIf { it.isNotEmpty() } ?: "Default text"
            val textSize = binding.textSizeEditText.text.toString()?.takeIf { it.isNotEmpty() }?.toIntOrNull() ?: 14
            val padding = binding.paddingEditText.text.toString()?.takeIf { it.isNotEmpty() }?.toIntOrNull() ?: 14
            val image = binding.imageEditText.text.toString()?.takeIf { it.isNotEmpty() } ?: "https://im.indiatimes.in/content/2022/Mar/Article-Body---2022-03-29T111113168_6244494def8d4.jpg"
            val backgroundColor = binding.backgroundColorEditText.text.toString()?.takeIf { it.isNotEmpty() } ?: "#FF000000"
            val textColor = binding.textColorEditText.text.toString()?.takeIf { it.isNotEmpty() } ?: "#FFFFFFFF"
            val cornerRadius = binding.cornerRadiusEditText.text.toString()?.takeIf { it.isNotEmpty() }?.toIntOrNull() ?: 6
            val tooltipWidth = binding.tooltipWidthEditText.text.toString()?.takeIf { it.isNotEmpty() }?.toIntOrNull() ?: 100
            val arrowWidth = binding.arrowWidthEditText.text.toString()?.takeIf { it.isNotEmpty() }?.toIntOrNull() ?: 10
            val arrowHeight = binding.arrowHeightEditText.text.toString()?.takeIf { it.isNotEmpty() }?.toIntOrNull() ?: 20

            val data = TooltipDataEntity(
                selectedItem,
                true,
                tooltipText,
                image,
                textSize,
                padding,
                backgroundColor,
                textColor,
                cornerRadius,
                tooltipWidth,
                arrowWidth,
                arrowHeight
            )
            sharedViewModel.insertItem(data)
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