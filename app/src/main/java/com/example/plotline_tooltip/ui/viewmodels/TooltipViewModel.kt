package com.example.plotline_tooltip.ui.viewmodels

import com.example.plotline_tooltip.data.repository.TooltipRepository
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.plotline_tooltip.data.db.TooltipDatabase
import com.example.plotline_tooltip.data.model.TooltipDataEntity

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TooltipViewModel(private val context: Context): ViewModel() {
    private var repository : TooltipRepository
    val allTooltipData : LiveData<List<TooltipDataEntity>>
    init {
        val dao = TooltipDatabase.getDatabase(context).getTooltipDao()
        repository = TooltipRepository(dao)
        allTooltipData = repository.allTooltipData()
    }
    fun insertItem(tooltipData: TooltipDataEntity) {
        viewModelScope.launch (Dispatchers.IO) {
            repository.insert(tooltipData)
        }
    }
}