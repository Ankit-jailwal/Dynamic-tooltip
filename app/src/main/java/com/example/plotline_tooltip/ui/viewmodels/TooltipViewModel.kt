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

class TooltipViewModel(private val context: Context): ViewModel() {
    private var repository : TooltipRepository
    val allTooltipData : LiveData<List<TooltipDataEntity>>
    init {
        val dao = TooltipDatabase.getDatabase(context).getTooltipDao()
        repository = TooltipRepository(dao)
        allTooltipData = repository.allTooltipData
    }

    fun getToolTipDataById(id: String) :  TooltipDataEntity? {
        println("Sizee: ${allTooltipData.value?.size}")
        val allTooltipDataList = allTooltipData.value ?: return null

        return allTooltipDataList.find { it.buttonId == id }
    }
    fun insertItem(tooltipData: TooltipDataEntity) = viewModelScope.launch(Dispatchers.IO) {
        println(tooltipData)
        repository.insert(tooltipData)
    }
}