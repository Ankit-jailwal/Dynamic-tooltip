package com.example.plotline_tooltip.ui.tooltip
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.view.*
import android.widget.*
import com.example.plotline_tooltip.R
import com.example.plotline_tooltip.data.model.TooltipData


class TooltipHelper(private val context: Context) {
    private var tooltipLayout: View? = null

    fun showTooltip(
        anchorView: View,
        tooltipText: String,
        tooltipProp: TooltipData?
    ) {
        val inflater = anchorView.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val tooltipView = inflater.inflate(R.layout.custom_tooltip_layout, null)
        val tooltipContainer = tooltipView.findViewById<RelativeLayout>(R.id.tooltipContainer)
        val tooltipTextView = tooltipView.findViewById<TextView>(R.id.tooltipTextView)
        tooltipTextView.text = tooltipProp?.text ?: tooltipText

        val arrowView = tooltipView.findViewById<View>(R.id.arrowView)

        tooltipProp?.textSize?.let { tooltipTextView.textSize = it.toFloat() }
        tooltipProp?.padding?.let { tooltipTextView.setPadding(it, it, it, it) }
        tooltipProp?.backgroundColor?.let { tooltipContainer.setBackgroundColor(Color.parseColor(it)) }
        tooltipProp?.textColor?.let { tooltipTextView.setTextColor(Color.parseColor(it)) }
//        tooltipProp?.toolTipWidth?.let {
//            val layoutParams = RelativeLayout.LayoutParams(it, RelativeLayout.LayoutParams.WRAP_CONTENT)
//            tooltipContainer.layoutParams = layoutParams
//        }
        tooltipProp?.arrowWidth?.let { arrowView.layoutParams.width = it }
        tooltipProp?.arrowHeight?.let { arrowView.layoutParams.height = it }

        val popupWindow = PopupWindow(
            tooltipView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        tooltipView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val tooltipWidth = tooltipView.measuredWidth
        val tooltipHeight = tooltipTextView.height + tooltipTextView.paddingTop + tooltipTextView.paddingBottom + arrowView.height

        val location = IntArray(2)
        anchorView.getLocationOnScreen(location)
        val anchorX = location[0]
        val anchorY = location[1]

        val tooltipX = anchorX + (anchorView.width / 2) - (tooltipWidth / 2)
        val tooltipY: Int
        val arrowMarginTop: Int
        val arrowMarginBottom: Int
        var arrowHeight = 64

        val screenHeight = Resources.getSystem().displayMetrics.heightPixels
        val isBottomRegionBoolean = anchorY > (screenHeight + 500) / 2
        if (isBottomRegionBoolean) {
            tooltipY = anchorY - anchorView.height
            arrowView.rotation = 180f
            println("arrowH ${arrowHeight}")
            arrowMarginBottom = tooltipHeight + tooltipTextView.paddingBottom
            arrowMarginTop = 0
        } else {
            tooltipY = anchorY + anchorView.height
            arrowMarginBottom = 0
            arrowMarginTop = tooltipHeight + tooltipTextView.paddingTop
        }

        val tooltipParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        tooltipParams.setMargins(0, arrowMarginTop, 0, arrowMarginBottom) // Add arrowView height to the bottom margin
        tooltipContainer.layoutParams = tooltipParams

        if (arrowView.parent != null) {
            (arrowView.parent as ViewGroup).removeView(arrowView)
        }

        val arrowParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        arrowParams.gravity = if(isBottomRegionBoolean) Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM else Gravity.CENTER_HORIZONTAL  // Align arrow to the bottom center
        arrowView.layoutParams = arrowParams

        if (arrowView.parent != null) {
            (arrowView.parent as ViewGroup).removeView(arrowView)
        }

        val isTooltipBelowScreen = true

        if (isTooltipBelowScreen) {
            (tooltipView as FrameLayout).addView(arrowView)
            popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, tooltipX, tooltipY)
        } else {
            (tooltipView as FrameLayout).addView(arrowView, 0)
            popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, tooltipX, tooltipY)
        }



        Log.d("dev","$anchorX  $anchorY  ")

    }

    fun hideTooltip() {
        tooltipLayout?.visibility = View.GONE
    }
}