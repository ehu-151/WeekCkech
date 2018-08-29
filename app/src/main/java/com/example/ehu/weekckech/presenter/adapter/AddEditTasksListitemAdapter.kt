package com.example.ehu.weekckech.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.R.id.spinner
import com.example.ehu.weekckech.data.sql.AddEditTaskItemModel
import com.example.ehu.weekckech.presenter.contract.AddEditTaskContract
import android.widget.ArrayAdapter



class AddEditTasksListitemAdapter(
        val mContext: Context,
        val rows: ArrayList<AddEditTaskItemModel>,
        val presenter: AddEditTaskContract.Presenter)
    : BaseAdapter() {

    private val inflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView
        when (rows[position].componentType) {
            AddEditTaskItemModel.TEXTVIEW -> {
                v = getViewTextView(position, convertView, parent)
            }
            AddEditTaskItemModel.SPINNER -> {
                v = getViewSpinner(position, convertView, parent)
            }
        }
        return v as View
    }

    private fun getViewTextView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView
        v = inflater.inflate(R.layout.add_edit_task_listitem_textview, null)
        val holder = AddEditTasksListitemAdapter.ItemHolderTextView(
                v.findViewById(R.id.imageView) as ImageView,
                v.findViewById(R.id.editText) as EditText
        )
        // リサイクルするときのためにタグ付けしておく
        v.tag = holder

        // itemのセット
        holder.imageView.setImageResource(rows[position].imageId)
        holder.title.hint = rows[position].hintText

        return v as View
    }

    private fun getViewSpinner(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var v = convertView
        v = inflater.inflate(R.layout.add_edit_task_listitem_spinner, null)
        val holder = AddEditTasksListitemAdapter.ItemHolderSpinner(
                v.findViewById(R.id.imageView) as ImageView,
                v.findViewById(R.id.spinner) as Spinner
        )
        // リサイクルするときのためにタグ付けしておく
        v.tag = holder

        // itemのセット
        holder.imageView.setImageResource(rows[position].imageId)
        setupSpinnerItem(holder.spinner,rows[position].spinnerItem)

        return v as View
    }

    fun setupSpinnerItem(spinner: Spinner, menu: ArrayList<String>?) {
        val adapter = ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter.addAll(menu)
        spinner.adapter=adapter
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

    class ItemHolderTextView(
            var imageView: ImageView,
            var title: EditText
    )

    class ItemHolderSpinner(
            var imageView: ImageView,
            var spinner: Spinner
    )
}