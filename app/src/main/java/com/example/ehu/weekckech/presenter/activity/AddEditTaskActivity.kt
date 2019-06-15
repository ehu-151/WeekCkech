package com.example.ehu.weekckech.presenter.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.ehu.weekckech.data.sql.TaskDataModel
import com.example.ehu.weekckech.view.fragment.AddEditTaskFragment
import java.lang.IllegalArgumentException


class AddEditTaskActivity : AppCompatActivity() {
    val EXTRA_TASK_ID = "task_id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.ehu.weekckech.R.layout.activity_add_edit_task)

        // StatusBarの色を変更
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// Android 5.0 Lollipop
            val window = getWindow()
            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            // finally change the color
            window.statusBarColor = ContextCompat.getColor(this, com.example.ehu.weekckech.R.color.colorSub)
        }

        // intent
        val extras = intent.extras
        val model = extras!!.getSerializable(EXTRA_TASK_ID) as TaskDataModel?
        initPersonFragment(model)
    }

    private fun initPersonFragment(model: TaskDataModel?) {
        // Fragmentのセット
        val fragment = AddEditTaskFragment.newInstance(model)
        val manege = supportFragmentManager.beginTransaction()
        manege.add(com.example.ehu.weekckech.R.id.add_fragment_space, fragment)
        manege.commit()
    }

    companion object {
        /**
         * pram:taskId:負の値なら新規タスク
         */
        fun createIntent(context: Context?, model: TaskDataModel? = null): Intent {
            var mIntent = Intent(context, AddEditTaskActivity::class.java)
            // 編集時はtaskIdをintentで渡す
            mIntent.putExtra(AddEditTaskActivity().EXTRA_TASK_ID, model)
            return mIntent
        }
    }
}
