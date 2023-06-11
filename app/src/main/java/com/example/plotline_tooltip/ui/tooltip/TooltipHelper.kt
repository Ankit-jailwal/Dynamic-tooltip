package com.example.plotline_tooltip.ui.tooltip
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.PathShape
import android.util.Log
import android.view.*
import android.widget.*
import com.example.plotline_tooltip.R
import com.example.plotline_tooltip.data.model.TooltipData
import com.example.plotline_tooltip.data.model.TooltipDataEntity


class TooltipHelper(private val context: Context) {
    private var tooltipLayout: View? = null

    fun showTooltip(
        anchorView: View,
        tooltipProp: TooltipDataEntity?
    ) {
        val inflater = anchorView.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val tooltipView = inflater.inflate(R.layout.custom_tooltip_layout, null)
        val tooltipContainer = tooltipView.findViewById<RelativeLayout>(R.id.tooltipContainer)
        val tooltipTextView = tooltipView.findViewById<TextView>(R.id.tooltipTextView)
        val arrowView = tooltipView.findViewById<View>(R.id.arrowView)
        tooltipTextView.text = tooltipProp?.text ?: "tooltipText"

        val path = Path()
        lateinit var shapeDrawable: ShapeDrawable
        tooltipProp?.arrowWidth?.let { width ->
            tooltipProp.arrowHeight?.let {height ->
            path.moveTo(width.toFloat() / 2, 0f)
            path.lineTo(width.toFloat(), height.toFloat())
            path.lineTo(0f, height.toFloat())
            path.lineTo(width.toFloat() / 2, 0f)
               shapeDrawable = ShapeDrawable(PathShape(path, width.toFloat(), height.toFloat()))
            }
        }

        path.close()

        shapeDrawable.paint.color = Color.parseColor(tooltipProp?.backgroundColor)

        val arrowDrawable: Drawable = shapeDrawable

        tooltipProp?.textSize?.let { tooltipTextView.textSize = it.toFloat() }
        tooltipProp?.padding?.let { tooltipTextView.setPadding(it, it, it, it) }
        tooltipProp?.textColor?.let { color ->
            val parsedColor = try {
                Color.parseColor(color)
            } catch (e: IllegalArgumentException) {
                Color.WHITE
            }
            tooltipTextView.setTextColor(parsedColor)
        }
        tooltipProp?.toolTipWidth?.let {
            tooltipTextView.width = it
        }

        val drawable = GradientDrawable()

        drawable.cornerRadius = tooltipProp?.cornerRadius?.toFloat() ?: 6F

        tooltipProp?.backgroundColor?.let { color ->
            val parsedColor = try {
                Color.parseColor(color)
            } catch (e: IllegalArgumentException) {
                Color.BLACK
            }
            drawable.setColor(parsedColor)
        }

        tooltipContainer.background = drawable
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

        val tooltipX = anchorX + (anchorView.width / 2) - (tooltipWidth / 2)
        val tooltipY: Int
        val arrowMarginTop: Int
        val arrowMarginBottom: Int
        val arrowHeight = tooltipProp?.arrowHeight ?: 50
        val arrowWidth = tooltipProp?.arrowWidth ?: 40

        val screenHeight = Resources.getSystem().displayMetrics.heightPixels
        val isBottomRegionBoolean = anchorY > (screenHeight + 500) / 2
        if (isBottomRegionBoolean) {
            tooltipY = screenHeight - tooltipHeight - anchorView.height / 2
            println("Height: ${tooltipHeight}")
            arrowView.rotation = 180f
            arrowMarginBottom = arrowHeight
            arrowMarginTop = 0
        } else {
            tooltipY = anchorY + anchorView.height
            arrowMarginBottom = 0
            arrowMarginTop = arrowHeight
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
        arrowView.layoutParams.width = arrowWidth
        arrowView.layoutParams.height = arrowHeight
        arrowView.background = arrowDrawable

        val isTooltipBelowScreen = false

        if (isTooltipBelowScreen) {
            (tooltipView as FrameLayout).addView(arrowView)
            popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, tooltipX, tooltipY)
        } else {
            (tooltipView as FrameLayout).addView(arrowView, 0)
            popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, tooltipX, tooltipY)
        }

    }

    fun hideTooltip() {
        tooltipLayout?.visibility = View.GONE
    }
}