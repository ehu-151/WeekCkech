package com.example.ehu.weekckech.view.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.DayListItemModel
import com.example.ehu.weekckech.presenter.activity.AddEditTaskActivity
import com.example.ehu.weekckech.presenter.activity.TasksMainActivity
import com.example.ehu.weekckech.presenter.adapter.TasksAdapter
import com.example.ehu.weekckech.presenter.contract.PagerDayConstract
import com.example.ehu.weekckech.presenter.presenter.PagerDayPresenter
import kotlinx.android.synthetic.main.pager_day.view.*
import se.emilsjolander.stickylistheaders.StickyListHeadersListView
import kotlin.collections.ArrayList

/**
 * PagerDayConstract.Viewの実装メソッドはPagerDayPresenterから呼ばれることを想定しています。
 *
 * ビューのクリックリスナーは基本的にこのクラスでセットしますが、ListViewのアダプターは例外です。
 * アダプター内でクリックリスナーをセットしています。
 */
class MainPagerDayFragment : Fragment(), PagerDayConstract.View {
    // セットする変数の宣言
    override var presenter: PagerDayConstract.Presenter = PagerDayPresenter(this)
    lateinit var listView: StickyListHeadersListView
    lateinit var floatingActionButton: FloatingActionButton


    private lateinit var mContext: Context
    override fun showDaysTasks(dayListItems: ArrayList<DayListItemModel>) {
        listView.adapter = TasksAdapter(mContext, dayListItems, presenter)
    }

    override fun showAddEditTask() {
        var mIntent = Intent(this.mContext, AddEditTaskActivity::class.java)
        startActivity(mIntent)
    }

    override fun showDayTasks() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pager_day, container, false)
    }

    override fun onResume() {
        super.onResume()
        // タスクのロード、ロジックはプレゼンターでやる
        presenter.start()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Contextの格納
        mContext = view.context
        // ListViewのセット
        listView = view.findViewById(R.id.listView)
        floatingActionButton = view.findViewById(R.id.add_task_floating_button)
        floatingActionButton.setOnClickListener {
            Log.d("setOnClickListener", "Add Task")
            presenter.addNewDayTask()
        }

    }

}
