package com.example.plotline_tooltip.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tooltip_data")
data class TooltipDataEntity(
    @PrimaryKey val buttonId: String,
    val isVisible: Boolean?,
    val text: String?,
    val textSize: Int?,
    val padding: Int?,
    val backgroundColor: String?,
    val textColor: String?,
    val cornerRadius: Int?,
    val toolTipWidth: Int?,
    val arrowWidth: Int?,
    val arrowHeight: Int?
)
