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
        val tooltipContainer = tooltipView.findViewById<RelativeLayout>(R.id.tooltipContainer)
        val tooltipTextView = tooltipView.findViewById<TextView>(R.id.tooltipTextView)
        tooltipTextView.text = tooltipText

        val arrowView = tooltipView.findViewById<View>(R.id.arrowView)

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

        val tooltipX = anchorX  +(anchorView.width / 2) - (tooltipWidth / 2)
        val tooltipY: Int
        val arrowMarginTop: Int

        val screenHeight = Resources.getSystem().displayMetrics.heightPixels

        if (anchorY > (screenHeight + 500) / 2) {
            tooltipY = anchorY - anchorView.height
            arrowMarginTop =  arrowView.height
        } else {
            tooltipY = anchorY + anchorView.height
            arrowMarginTop = tooltipHeight + tooltipTextView.paddingTop
        }

        val tooltipParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        tooltipParams.setMargins(0, arrowMarginTop, 0, 0)
        tooltipContainer.layoutParams = tooltipParams

        if (arrowView.parent != null) {
            (arrowView.parent as ViewGroup).removeView(arrowView)
        }

        val arrowParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        arrowParams.gravity = Gravity.CENTER_HORIZONTAL
        arrowView.layoutParams = arrowParams

        if (arrowView.parent != null) {
            (arrowView.parent as ViewGroup).removeView(arrowView)
        }

        val isTooltipBelowScreen = true

        if (isTooltipBelowScreen) {
            (tooltipView as FrameLayout).addView(arrowView)
        } else {
            (tooltipView as FrameLayout).addView(arrowView, 0)
        }

        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, tooltipX, tooltipY)

    }

    fun hideTooltip() {
        tooltipLayout?.visibility = View.GONE
    }
}