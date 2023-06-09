package com.example.plotline_tooltip.ui.viewmodels

import TooltipDataRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plotline_tooltip.data.model.TooltipDataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TooltipViewModel(private val repository: TooltipDataRepository): ViewModel() {
    val allTooltipData: LiveData<List<TooltipDataEntity>> = repository.allTooltipData

    fun insertTooltipData(tooltipData: TooltipDataEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTooltipData(tooltipData)
        }
    }

    fun deleteTooltipData(buttonId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTooltipData(buttonId)
        }
    }
}