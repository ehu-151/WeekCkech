package com.example.ehu.weekckech.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.TaskDataModel
import com.example.ehu.weekckech.databinding.PagerDayBinding
import com.example.ehu.weekckech.presenter.activity.AddEditTaskActivity
import com.example.ehu.weekckech.presenter.adapter.TasksAdapter
import com.example.ehu.weekckech.presenter.contract.PagerDayConstract
import com.example.ehu.weekckech.presenter.presenter.PagerDayPresenter


/**
 * PagerDayConstract.Viewの実装メソッドはPagerDayPresenterから呼ばれることを想定しています。
 *
 * ビューのクリックリスナーは基本的にこのクラスでセットしますが、ListViewのアダプターは例外です。
 * アダプター内でクリックリスナーをセットしています。
 */
class MainPagerDayFragment : Fragment(), PagerDayConstract.View {
    // セットする変数の宣言
    override lateinit var presenter: PagerDayConstract.Presenter
    lateinit var binding: PagerDayBinding
    private lateinit var mContext: Context

    override fun showDaysTasks(taskDataModel: ArrayList<TaskDataModel>) {
        binding.listView.adapter = TasksAdapter(mContext, taskDataModel, presenter)
    }

    override fun showAddEditTask() {
        var mIntent = Intent(this.mContext, AddEditTaskActivity::class.java)
        startActivity(mIntent)
    }

    override fun showDayTasks() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.pager_day, container, false)
        val root = binding.root
        return root
    }

    override fun onResume() {
        super.onResume()
        // タスクのロード、ロジックはプレゼンターでやる
        presenter.start()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter=PagerDayPresenter(this,view.context)
        binding.presenter = presenter
        // Contextの格納
        mContext = view.context
        presenter.loadDaysTasks()
    }
}
