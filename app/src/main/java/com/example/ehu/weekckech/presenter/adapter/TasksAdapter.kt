package com.example.ehu.weekckech.presenter.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.TaskDataModel
import com.example.ehu.weekckech.presenter.contract.PagerDayConstract
import com.example.ehu.weekckech.util.TaskNotification
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter

/**
 * MainPagerDayFragmentから呼ばれることを想定しています。
 *
 * items: ArrayList<TaskDataModel>を引数にとり、モデルをListItemに変換します。
 * itemsに入っているモデルの数だけ、ListItemを生成します。
 * クリックリスナーをセットしています。
 */
class TasksAdapter(
        val context: Context, private val rows: ArrayList<TaskDataModel>, val presenter: PagerDayConstract.Presenter)
    : BaseAdapter(), StickyListHeadersAdapter {

    val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getHeaderId(position: Int): Long {
        return getHeaderItem(position).toLong()
    }

    /**
     * weekGroupからGroupの数値を返します。
     */
    private fun getHeaderItem(position: Int): Int {
        return rows[position].weekGroup
    }

    override fun getHeaderView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView

        // Headerとitemの条件分岐

        // 前の日付グループと同じならlist itemの追加
        v = inflater.inflate(R.layout.pager_day_listheader, null)
        var holder = HeaderHolder(
                v?.findViewById(R.id.header_title) as TextView
        )
        // リサイクルするときのためにタグ付けしておく
        v.tag = holder

        // itemのセット
        holder.headerName.text = TaskDataModel.toDayOfWeek(rows[position].weekGroup)

        return v as View
    }


    override fun getItem(p0: Int): Any {
        return rows[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return rows.size
    }

    //Dataクラス
    var taskDataModel: ArrayList<TaskDataModel>? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView

        // Headerとitemの条件分岐

        // 前の日付グループと同じならlist itemの追加
        v = inflater.inflate(R.layout.pager_day_listitem, null)
        val holder = ItemHolder(
                v.findViewById(R.id.pager_day_listitem_checkbox) as CheckBox,
                v.findViewById(R.id.pager_day_listitem_detail) as TextView
        )
        // リサイクルするときのためにタグ付けしておく
        v.tag = holder

        // itemのセット
        holder.checkBox.isChecked = rows[position].isChecked
        holder.detail.text = rows[position].detail

        // クリックアダプターのセット
        v.findViewById<ConstraintLayout>(R.id.click_area).setOnClickListener {
            Log.d("setOnClickListener", "item_layout:$position")
            TaskNotification(context).notifyTaskNowLimit(rows[position])
            presenter.editDayTask(rows[position])
        }
        v.findViewById<CheckBox>(R.id.pager_day_listitem_checkbox).setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("setOnClickListener", "item_layout:$position")
            // activate/off切り替え
            if (isChecked) {
                presenter.checkTask(rows[position].taskId!!)
            } else {
                presenter.unCheckTask(rows[position].taskId!!)
            }
        }

        return v as View
    }


    fun setTaskList(listItem: ArrayList<TaskDataModel>) {
        taskDataModel = listItem
    }

    class ItemHolder(
            var checkBox: CheckBox,
            var detail: TextView
    )

    // 表示するビューの型指定
    class HeaderHolder(
            var headerName: TextView
    )

}