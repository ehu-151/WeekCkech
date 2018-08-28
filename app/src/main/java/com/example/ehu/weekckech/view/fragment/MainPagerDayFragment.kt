package com.example.ehu.weekckech.view.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.DayListItemModel
import com.example.ehu.weekckech.presenter.adapter.TasksAdapter
import com.example.ehu.weekckech.presenter.contract.PagerDayConstract
import com.example.ehu.weekckech.presenter.presenter.PagerDayPresenter
import kotlinx.android.synthetic.main.pager_day.view.*
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
    lateinit var listView: ListView
    lateinit var listHader: ListView

    private lateinit var mContext: Context
    override fun showDaysTasks(dayListItems: ArrayList<DayListItemModel>) {
        // セット
//        val header = View.inflate(mContext, R.layout.pager_day_listheader, null)
//        listView.addHeaderView(header, null, false)
        listView.adapter = TasksAdapter(mContext, dayListItems)
    }

    override fun showAddEditTask() {

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
        listHader = view.findViewById(R.id.listView)
    }

}
