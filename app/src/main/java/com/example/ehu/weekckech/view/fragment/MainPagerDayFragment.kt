package com.example.ehu.weekckech.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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

    override fun showDaysTasks(taskDataModel: MutableLiveData<ArrayList<TaskDataModel>>) {
        taskDataModel.observe(this, Observer {
            if (it.size == 0) {
                showSwitchNoTaskWarning(true)
            } else {
                showSwitchNoTaskWarning(false)
                binding.listView.adapter = TasksAdapter(mContext, it, presenter)
            }
        })
    }

    override fun showAddTask() {
        startActivity(AddEditTaskActivity.createIntent(context))
    }

    override fun showEditTask(model: TaskDataModel) {
        startActivity(AddEditTaskActivity.createIntent(context, model))
    }

    override fun showDayTasks() {

    }

    override fun showSwitchNoTaskWarning(isWarning: Boolean) {
        if (isWarning) {
            binding.listView.visibility = View.GONE
            binding.noTaskWarningLayout.visibility = View.VISIBLE
        } else {
            binding.listView.visibility = View.VISIBLE
            binding.noTaskWarningLayout.visibility = View.GONE
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.pager_day, container, false)
        val root = binding.root
        return root
    }

    override fun onResume() {
        super.onResume()
        // タスクのロード
        presenter.start()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = PagerDayPresenter(this, view.context)
        binding.presenter = presenter
        // Contextの格納
        mContext = view.context
    }
}
