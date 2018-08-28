package com.example.ehu.weekckech.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.ehu.weekckech.R

class TaskHeaderAdapter(var context: Context, var headerName: String)
    : BaseAdapter() {
    val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v=convertView
        var holder: TaskHeaderAdapter.CustomViewHolder? = null
        v = inflater.inflate(R.layout.pager_day_listitem, null)
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
    // 表示するビューの型指定
    class CustomViewHolder(
            var headerName:TextView
    )
}