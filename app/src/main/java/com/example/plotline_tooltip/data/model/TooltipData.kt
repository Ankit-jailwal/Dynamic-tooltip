package com.example.plotline_tooltip.data.model

data class TooltipData(val buttonId: String,
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