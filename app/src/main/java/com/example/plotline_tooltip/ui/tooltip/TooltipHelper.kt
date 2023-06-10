package com.example.plotline_tooltip.ui.tooltip
import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.plotline_tooltip.R


class TooltipHelper(private val context: Context) {
    private var tooltipLayout: View? = null

    fun showTooltip(anchorView: View, tooltipText: String) {
        val inflater = anchorView.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val tooltipView = inflater.inflate(R.layout.custom_tooltip_layout, null)
        val tooltipTextView = tooltipView.findViewById<TextView>(R.id.tooltipTextView)
        tooltipTextView.text = tooltipText

        val popupWindow = PopupWindow(
            tooltipView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )
        tooltipView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val tooltipWidth = tooltipView.measuredWidth
        val tooltipHeight = tooltipView.measuredHeight

        val location = IntArray(2)
        anchorView.getLocationOnScreen(location)
        val anchorX = location[0]
        val anchorY = location[1]

        val tooltipX = anchorX + anchorView.width / 2 - tooltipWidth / 2
        val tooltipY = anchorY + anchorView.height

        val screenHeight = Resources.getSystem().displayMetrics.heightPixels
        val tooltipBottomY = tooltipY + tooltipHeight
        val isTooltipBelowScreen = tooltipBottomY > screenHeight

        if (isTooltipBelowScreen) {
            val adjustedTooltipY = anchorY - tooltipHeight
            popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, tooltipX, adjustedTooltipY)
        } else {
            popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, tooltipX, tooltipY)
        }

        val arrowView = tooltipView.findViewById<View>(R.id.arrowView)

        val arrowX = tooltipWidth / 2 - arrowView.width / 2
        val arrowParams = arrowView.layoutParams as RelativeLayout.LayoutParams

        // Center the arrow horizontally
        arrowParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
        arrowParams.setMargins(arrowX, 0, 0, 0)

        arrowView.layoutParams = arrowParams
        Log.d("dev","$anchorX  $anchorY  $arrowX")

    }

    fun hideTooltip() {
        tooltipLayout?.visibility = View.GONE
    }
}