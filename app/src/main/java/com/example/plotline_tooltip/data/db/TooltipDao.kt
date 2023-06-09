package com.example.plotline_tooltip.data.db

import com.example.plotline_tooltip.data.model.TooltipDataEntity
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TooltipDao {
    @Query("SELECT * FROM tooltip_data")
    fun getAllTooltipData(): LiveData<List<TooltipDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTooltipData(tooltipData: TooltipDataEntity)

    @Update
    suspend fun updateTooltipData(tooltipData: TooltipDataEntity)
}