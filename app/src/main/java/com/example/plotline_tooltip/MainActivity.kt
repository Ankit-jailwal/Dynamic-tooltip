package com.example.plotline_tooltip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.plotline_tooltip.ui.fragments.TooltipEditorFragment
import com.example.plotline_tooltip.ui.viewmodels.TooltipViewModel
import com.example.plotline_tooltip.ui.viewmodels.TooltipViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var sharedViewModel: TooltipViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedViewModel = ViewModelProvider(this, TooltipViewModelFactory(this)).get(TooltipViewModel::class.java)

        if (savedInstanceState == null) {
            showToolTipEditor()
        }
    }

    private fun showToolTipEditor() {
        val fragment = TooltipEditorFragment()
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}