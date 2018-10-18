package com.example.ehu.weekckech.view.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.AddEditTaskItemModel
import com.example.ehu.weekckech.presenter.adapter.AddEditTasksListitemAdapter
import com.example.ehu.weekckech.presenter.contract.AddEditTaskContract
import com.example.ehu.weekckech.presenter.presenter.AddEditTaskPresenter


class AddEditTaskFragment : Fragment(), AddEditTaskContract.View {
    val TAG = "AddEditTaskFragment"
    lateinit var mContext: Context
    lateinit var listView: ListView
    lateinit var saveButton: Button
    lateinit var leaveButton: Button
    override var presenter: AddEditTaskContract.Presenter = AddEditTaskPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_edit_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Contextの格納
        mContext = view.context
        // ListViewのセット
        listView = view.findViewById(R.id.listView)
        // Buttonのセット
        saveButton = view.findViewById(R.id.edit_save_button)
        leaveButton = view.findViewById(R.id.edit_leave_button)
        // clickadapter
        saveButton.setOnClickListener { Log.v(TAG, "clicked") }
        leaveButton.setOnClickListener { Log.v(TAG, "clicked") }

        presenter.start()
    }

    override fun showTaskConfigEditRow(listItemModel: ArrayList<AddEditTaskItemModel>) {
        listView.adapter = AddEditTasksListitemAdapter(mContext, listItemModel, presenter)
    }

    override fun showTasksMain() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
