package com.example.ehu.weekckech.presenter.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.AddEditTaskItemModel
import com.example.ehu.weekckech.presenter.contract.AddEditTaskContract

class AddEditTasksListitemAdapter(mContext: Context, rows: ArrayList<AddEditTaskItemModel>, presenter: AddEditTaskContract.Presenter) : BaseAdapter() {

    private val inflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView

        v = inflater.inflate(R.layout.pager_day_listitem, null)
        val holder = TasksAdapter.ItemHolder(
                v.findViewById(R.id.pager_day_listitem_checkbox) as CheckBox,
                v.findViewById(R.id.pager_day_listitem_title) as TextView,
                v.findViewById(R.id.pager_day_listitem_detail) as TextView
        )
        // リサイクルするときのためにタグ付けしておく
        v.tag = holder

        // itemのセット
//        holder.checkBox.isChecked = rows[position].isChecked
//        holder.title.text = rows[position].title
//        holder.detail.text = rows[position].detail

        // クリックアダプターのセット
        v.findViewById<ConstraintLayout>(R.id.constraintLayout3).setOnClickListener {
            Log.d("setOnClickListener", "item_layout:" + position.toString())
        }

        return v as View
    }

    override fun getItem(p0: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(p0: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ItemHolderTextView(
            var imageView: ImageView,
            var title: TextView
    )
    class ItemHolderSpinner(
            var imageView: ImageView,
            var spinner: Spinner
    )
}