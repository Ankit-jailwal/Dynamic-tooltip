package com.example.plotline_tooltip.ui.tooltip
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.plotline_tooltip.R


class TooltipHelper(private val context: Context) {
    private var tooltipLayout: View? = null
    private var tooltipText: TextView? = null
    private var tooltipImage: ImageView? = null

    fun showTooltip(view: View, text: String, imageResId: Int) {
        if (tooltipLayout == null) {
            tooltipLayout = LayoutInflater.from(context).inflate(R.layout.layout_tooltip, null)
            tooltipText = tooltipLayout?.findViewById(R.id.tooltipText)
            tooltipImage = tooltipLayout?.findViewById(R.id.tooltipImage)
        }

        tooltipText?.text = text
        tooltipImage?.setImageDrawable(ContextCompat.getDrawable(context, imageResId))

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val parent = view.parent as LinearLayout
        if (parent.getChildAt(parent.childCount - 1) != tooltipLayout) {
            parent.addView(tooltipLayout, params)
        }

        tooltipLayout?.visibility = View.VISIBLE
    }

    fun hideTooltip() {
        tooltipLayout?.visibility = View.GONE
    }
}