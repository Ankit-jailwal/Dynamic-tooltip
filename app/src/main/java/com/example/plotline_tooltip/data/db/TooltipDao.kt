package com.example.plotline_tooltip.data.db

import com.example.plotline_tooltip.data.model.TooltipDataEntity
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TooltipDao {
    @Query("SELECT * FROM tooltip_data")
    fun getAllTooltipData(): LiveData<List<TooltipDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTooltipData(tooltipData: TooltipDataEntity)

    @Query("DELETE FROM tooltip_data WHERE buttonId = :buttonId")
    suspend fun deleteTooltipData(buttonId: String)
}