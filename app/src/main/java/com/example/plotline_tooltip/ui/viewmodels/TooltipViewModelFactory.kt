package com.example.plotline_tooltip.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TooltipViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TooltipViewModel::class.java)) {
            return TooltipViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}