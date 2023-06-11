package com.example.plotline_tooltip.data.repository

import androidx.lifecycle.LiveData
import com.example.plotline_tooltip.data.db.TooltipDao
import com.example.plotline_tooltip.data.model.TooltipDataEntity

class TooltipRepository(private val tooltipDao: TooltipDao) {
    fun allTooltipData(): LiveData<List<TooltipDataEntity>> = tooltipDao.getAllTooltipData()

    suspend fun insert(tooltipData: TooltipDataEntity) = tooltipDao.insertTooltipData(tooltipData)
}