package com.example.plotline_tooltip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.plotline_tooltip.ui.fragments.RendererFragment
import com.example.plotline_tooltip.ui.fragments.TooltipEditorFragment
import com.example.plotline_tooltip.ui.viewmodels.TooltipViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var sharedViewModel: TooltipViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedViewModel = ViewModelProvider(this).get(TooltipViewModel::class.java)

        if (savedInstanceState == null) {
            showToolTipEditor()
        }
    }

    private fun showToolTipEditor() {
        val fragment = TooltipEditorFragment()
        replaceFragment(fragment)
    }

    private fun showRenderer() {
        val fragment = RendererFragment()
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}