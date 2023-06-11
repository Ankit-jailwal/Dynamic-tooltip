package com.example.plotline_tooltip.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tooltip_table_prod")
data class TooltipDataEntity(
    @ColumnInfo(name = "buttonId") @PrimaryKey val buttonId: String,
    @ColumnInfo(name = "isVisible") val isVisible: Boolean?,
    @ColumnInfo(name = "text") val text: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "textSize") val textSize: Int?,
    @ColumnInfo(name = "padding") val padding: Int?,
    @ColumnInfo(name = "backgroundColor") val backgroundColor: String?,
    @ColumnInfo(name = "textColor") val textColor: String?,
    @ColumnInfo(name = "cornerRadius") val cornerRadius: Int?,
    @ColumnInfo(name = "toolTipWidth") val toolTipWidth: Int?,
    @ColumnInfo(name = "arrowWidth") val arrowWidth: Int?,
    @ColumnInfo(name = "arrowHeight") val arrowHeight: Int?
)
