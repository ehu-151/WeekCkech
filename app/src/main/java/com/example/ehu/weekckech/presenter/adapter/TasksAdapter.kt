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
        var context: Context, var rows: ArrayList<DayListItemModel>)
    : BaseAdapter() {
    val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    var currentGroup:String = "日"
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
        var v=convertView

        // Headerとitemの条件分岐
        if (rows[position].weekGroup==currentGroup){
            // 前の日付グループと同じならlistitemの追加
            v = inflater.inflate(R.layout.pager_day_listitem, null)
            var holder= TasksAdapter.ItemHolder(
                    v?.findViewById(R.id.pager_day_listitem_checkbox) as CheckBox,
                    v?.findViewById(R.id.pager_day_listitem_title) as TextView,
                    v?.findViewById(R.id.pager_day_listitem_detail) as TextView
            )
            // リサイクルするときのためにタグ付けしておく
            v.tag=holder

            // itemのセット
            holder.checkBox.isChecked=rows[position].isChecked
            holder.title.text=rows[position].title
            holder.detail.text=rows[position].detail

            // クリックアダプターのセット
//            v.findViewById<ConstraintLayout>(R.id.constraintLayout3).setOnClickListener {
//                Log.d("TAG_A",position.toString())
//            }
        }else{
            v = inflater.inflate(R.layout.pager_day_listheader, null)
            // CustomViewHolderとlayoutの関連付け
            var holder = TasksAdapter.HeaderHolder(
                    v.findViewById(R.id.header_title) as TextView,
                    v.findViewById(R.id.header_imageView) as ImageView
            )
            // リサイクルするときのためにタグ付けしておく
            v.tag = holder

            // headerNameのセット
            holder.headerName.text = rows[position].weekGroup

            // クリックアダプターのセット
//            v.findViewById<ConstraintLayout>(R.id.header_imageView).setOnClickListener {
//                Log.d("setOnClickListener", "header_imageView:"+position.toString())
//            }
        }
        currentGroup=rows[position].weekGroup

        return v as View
    }


    fun setTaskList(listItem: ArrayList<DayListItemModel>) {
        dayListItemModel = listItem
    }

    class ItemHolder(
            var checkBox: CheckBox,
            var title:TextView,
            var detail:TextView
    )
    // 表示するビューの型指定
    class HeaderHolder(
            var headerName: TextView,
            var addImageView: ImageView
    )

}