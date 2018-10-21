package com.example.ehu.weekckech.view.fragment


import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.AddEditTaskItemModel
import com.example.ehu.weekckech.presenter.contract.AddEditTaskContract
import com.example.ehu.weekckech.presenter.presenter.AddEditTaskPresenter


class AddEditTaskFragment : Fragment(), AddEditTaskContract.View {
    val TAG = "AddEditTaskFragment"
    lateinit var mContext: Context
    lateinit var mView: View
    lateinit var title:String
    lateinit var detail:String
    lateinit var limitTime:String
    lateinit var notificationTime:String
    lateinit var weekGroup:String
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
        mView=view

        // Buttonのセット
        saveButton = view.findViewById(R.id.edit_save_button)
        leaveButton = view.findViewById(R.id.edit_leave_button)
        // clickadapter
        saveButton.setOnClickListener { presenter.saveTask() }
        leaveButton.setOnClickListener { showTasksMain() }

        presenter.start()
    }

    override fun showTaskConfigEditRow(lists: ArrayList<AddEditTaskItemModel>) {
       for (list in lists) {
           val model = AddEditTaskItemModel
           var layout: ConstraintLayout = mView.findViewById(list.layoutid)
           layout.findViewById<ImageView>(R.id.imageView).setImageResource(list.imageId)
            if (list.componentType==model.TEXTVIEW){
                layout.findViewById<EditText>(R.id.editText).hint=list.hintText
            }else if (list.componentType==model.SPINNER){
                // スピナーのレイアウト指定
                val adapter = ArrayAdapter<String>(mContext, R.layout.spinner_item)
                // プルダウンレイアウト指定
                adapter.setDropDownViewResource(R.layout.spinner_item)
                adapter.addAll(list.spinnerItem)
                layout.findViewById<Spinner>(R.id.spinner).adapter=adapter
            }
    }

    }

    override fun showTasksMain() {
        activity?.finish()
    }
    fun setItem(){

    }
}
