package com.example.ehu.weekckech.presenter.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.DayListItemModel

/**
 * MainPagerDayFragmentから呼ばれることを想定しています。
 *
 * items: ArrayList<DayListItemModel>を引数にとり、モデルをListItemに変換します。
 * itemsに入っているモデルの数だけ、ListItemを生成します。
 * クリックリスナーをセットしています。
 */
class TasksAdapter(
        var context: Context, var items: ArrayList<DayListItemModel>)
    : BaseAdapter() {
    val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getItem(p0: Int): Any {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    //Dataクラス
    var dayListItemModel: ArrayList<DayListItemModel>? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v=convertView
        var holder: CustomViewHolder? = null
        v = inflater.inflate(R.layout.pager_day_listitem, null)
        holder= CustomViewHolder(
                v?.findViewById(R.id.pager_day_listitem_checkbox) as CheckBox,
                v?.findViewById(R.id.pager_day_listitem_title)as TextView,
                v?.findViewById(R.id.pager_day_listitem_detail)as TextView
        )
        // リサイクルするときのためにタグ付けしておく
        v.tag=holder

        // itemのセット
        holder.checkBox.isChecked=items[position].isChecked
        holder.title.text=items[position].title
        holder.detail.text=items[position].detail

        // クリックアダプターのセット
        v.findViewById<ConstraintLayout>(R.id.constraintLayout3).setOnClickListener {
            Log.d("TAG_A",position.toString())
        }

        return v as View
    }


    fun setTaskList(listItem: ArrayList<DayListItemModel>) {
        dayListItemModel = listItem
    }

    class CustomViewHolder(
            var checkBox: CheckBox,
            var title:TextView,
            var detail:TextView
    )

}