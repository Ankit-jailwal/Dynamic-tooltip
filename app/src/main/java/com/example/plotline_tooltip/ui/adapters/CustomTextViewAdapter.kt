package com.example.plotline_tooltip.ui.adapters

import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.BindingAdapter

private const val MARGIN_TOP = 7
private const val WIDTH = 0
private const val HEIGHT = 32
private const val PADDING_VERTICAL = 5
private const val PADDING_HORIZONTAL = 12

@BindingAdapter("customEditText")
fun EditText.setCustomSize(hint: String, editText: EditText) {

    val layoutParams = editText.layoutParams as ViewGroup.MarginLayoutParams

    layoutParams.topMargin = MARGIN_TOP

    layoutParams.width = WIDTH

    layoutParams.height = HEIGHT

    editText.layoutParams = layoutParams

    editText.setPadding(PADDING_VERTICAL, editText.paddingTop, PADDING_VERTICAL, editText.paddingBottom)

    editText.setPadding(editText.paddingLeft, PADDING_HORIZONTAL, editText.paddingRight, PADDING_HORIZONTAL)

    hint?.let {
        editText.hint = it
    }
}
