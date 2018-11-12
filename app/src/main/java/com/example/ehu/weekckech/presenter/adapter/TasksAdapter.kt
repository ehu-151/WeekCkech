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
import com.example.ehu.weekckech.data.sql.DayListItemModel
import com.example.ehu.weekckech.presenter.contract.PagerDayConstract
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter

/**
 * MainPagerDayFragmentから呼ばれることを想定しています。
 *
 * items: ArrayList<DayListItemModel>を引数にとり、モデルをListItemに変換します。
 * itemsに入っているモデルの数だけ、ListItemを生成します。
 * クリックリスナーをセットしています。
 */
class TasksAdapter(
        val context: Context, val rows: ArrayList<DayListItemModel>, val presenter: PagerDayConstract.Presenter)
    : BaseAdapter(), StickyListHeadersAdapter {

    val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getHeaderId(position: Int): Long {
        return getHeaderItem(position).toLong()
    }

    /**
     * weekGroupからGroupの数値を返します。
     */
    fun getHeaderItem(posion: Int): Int {
        return when (rows[posion].weekGroup) {
            "月" -> 0
            "火" -> 1
            "水" -> 2
            "木" -> 3
            "金" -> 4
            "土" -> 5
            "日" -> 6
            else -> 7
        }
    }

    override fun getHeaderView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView

        // Headerとitemの条件分岐

        // 前の日付グループと同じならlistitemの追加
        v = inflater.inflate(R.layout.pager_day_listheader, null)
        var holder = TasksAdapter.HeaderHolder(
                v?.findViewById(R.id.header_title) as TextView
        )
        // リサイクルするときのためにタグ付けしておく
        v.tag = holder

        // itemのセット
        holder.headerName.text = rows[position].weekGroup

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
    var dayListItemModel: ArrayList<DayListItemModel>? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView

        // Headerとitemの条件分岐

        // 前の日付グループと同じならlistitemの追加
        v = inflater.inflate(R.layout.pager_day_listitem, null)
        val holder = TasksAdapter.ItemHolder(
                v.findViewById(R.id.pager_day_listitem_checkbox) as CheckBox,
                v.findViewById(R.id.pager_day_listitem_detail) as TextView
        )
        // リサイクルするときのためにタグ付けしておく
        v.tag = holder

        // itemのセット
        holder.checkBox.isChecked = rows[position].isChecked
        holder.detail.text = rows[position].detail

        // クリックアダプターのセット
        v.findViewById<ConstraintLayout>(R.id.constraintLayout3).setOnClickListener {
            Log.d("setOnClickListener", "item_layout:" + position.toString())
        }

        return v as View
    }


    fun setTaskList(listItem: ArrayList<DayListItemModel>) {
        dayListItemModel = listItem
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