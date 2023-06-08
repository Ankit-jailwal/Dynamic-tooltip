package com.example.plotline_tooltip.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.plotline_tooltip.R
import com.example.plotline_tooltip.ui.viewmodels.TooltipViewModel

class TooltipEditorFragment : Fragment() {

    private lateinit var sharedViewModel: TooltipViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tooltip_editor, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(TooltipViewModel::class.java)

        return view
    }
}