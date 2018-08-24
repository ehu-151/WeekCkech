package com.example.ehu.weekckech.view.fragment


import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.DayListItemModel
import com.example.ehu.weekckech.presenter.adapter.TasksAdapter
import com.example.ehu.weekckech.presenter.contract.PagerDayConstract
import com.example.ehu.weekckech.presenter.presenter.PagerDayPresenter
import java.util.*
import kotlin.collections.ArrayList


class MainPagerDayFragment : Fragment(), PagerDayConstract.View {
    override var presenter: PagerDayConstract.Presenter=PagerDayPresenter(this)
    lateinit var listView:ListView

    private lateinit var mContext: Context
    override fun showDaysTasks(dayListItems:ArrayList<DayListItemModel>) {
//        dayListItems.add(DayListItemModel(false, "タイトル1", "詳細1"))
        var tasksAdapter = TasksAdapter(mContext, dayListItems)
        listView.adapter = tasksAdapter
    }

    override fun showAddEditTask() {
        var temp = ""
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
        mContext = view.context
        // ListItemのセット
        listView = view.findViewById(R.id.listView)
        // ListItemのClickListenerのセット
        listView.onItemClickListener= AdapterView.OnItemClickListener { adapterView, view, pos, id ->
            Toast.makeText(mContext,  Integer.toString(pos), Toast.LENGTH_SHORT).show()
        }


    }

}
