package com.example.plotline_tooltip.ui.viewmodels

import TooltipRepository
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.plotline_tooltip.data.db.TooltipDatabase
import com.example.plotline_tooltip.data.model.TooltipDataEntity

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TooltipViewModel(val context: Context): ViewModel() {
    private var repository : TooltipRepository
    init {
        val dao = TooltipDatabase.getDatabase(context).getTooltipDao()
        repository = TooltipRepository(dao)
    }

    fun insertItem(tooltipData: TooltipDataEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(tooltipData)
    }
}