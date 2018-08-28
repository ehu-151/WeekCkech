package com.example.ehu.weekckech.presenter.activity

import android.app.Fragment
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.view.fragment.AddEditFragment
import android.graphics.Color.parseColor
import android.view.WindowManager
import android.os.Build
import android.support.v4.content.ContextCompat


class AddEditTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_edit)

        // StatusBarの色を変更
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// Android 5.0 Lollipop
            val window = getWindow()
            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            // finally change the color
            window.statusBarColor = ContextCompat.getColor(this,R.color.colorSub)
        }
        // Fragmentのセット
        val fragment= AddEditFragment()
        val manege = supportFragmentManager.beginTransaction()
//        manege.add(fragment)
    }
}
