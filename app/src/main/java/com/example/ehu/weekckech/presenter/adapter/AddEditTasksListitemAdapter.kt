package com.example.ehu.weekckech.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.AddEditTaskItemModel
import com.example.ehu.weekckech.presenter.contract.AddEditTaskContract


class AddEditTasksListitemAdapter(
        val mContext: Context,
        val rows: ArrayList<AddEditTaskItemModel>,
        val presenter: AddEditTaskContract.Presenter)
    : BaseAdapter() {

    private val inflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView
        when (rows[position].componentType) {
            AddEditTaskItemModel.EDITTEXT -> {
                v = getViewEditText(position, convertView, parent)
            }
            AddEditTaskItemModel.SPINNER -> {
                v = getViewSpinner(position, convertView, parent)
            }
            AddEditTaskItemModel.TEXTVIEW-> {
                v = getViewTextView(position,convertView,parent)
            }
        }
        return v as View
    }

    private fun getViewEditText(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView
        v = inflater.inflate(R.layout.add_edit_task_listitem_edittext, null)
        val holder = AddEditTasksListitemAdapter.ItemHolderEditText(
                v.findViewById(R.id.imageView) as ImageView,
                v.findViewById(R.id.editText) as EditText
        )
        // リサイクルするときのためにタグ付けしておく
        v.tag = holder

        // itemのセット
        holder.imageView.setImageResource(rows[position].imageId)
        holder.editText.hint = rows[position].hintText

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
    private fun getViewTextView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView
        v = inflater.inflate(R.layout.add_edit_task_listitem_textview, null)
        val holder = AddEditTasksListitemAdapter.ItemHolderTextView(
                v.findViewById(R.id.imageView) as ImageView,
                v.findViewById(R.id.textView) as TextView
        )
        // リサイクルするときのためにタグ付けしておく
        v.tag = holder

        // itemのセット
        holder.imageView.setImageResource(rows[position].imageId)
        holder.textView.text = rows[position].text
        holder.textView.setOnClickListener{presenter.enterTimePicker()}

        return v as View
    }

    fun setupSpinnerItem(spinner: Spinner, menu: ArrayList<String>?) {
        // スピナーのレイアウト指定
        val adapter = ArrayAdapter<String>(mContext, R.layout.spinner_item)
        // プルダウンレイアウト指定
        adapter.setDropDownViewResource(R.layout.spinner_item)
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

    class ItemHolderEditText(
            var imageView: ImageView,
            var editText: EditText
    )

    class ItemHolderSpinner(
            var imageView: ImageView,
            var spinner: Spinner
    )
    class ItemHolderTextView(
            var imageView: ImageView,
            var textView: TextView
    )
}