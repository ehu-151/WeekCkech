package com.example.ehu.weekckech.view.fragment


import android.app.Dialog
import android.app.DialogFragment
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.AddEditTaskItemModel
import com.example.ehu.weekckech.data.sql.TaskDataModel
import com.example.ehu.weekckech.presenter.contract.AddEditTaskContract
import com.example.ehu.weekckech.presenter.presenter.AddEditTaskPresenter
import java.util.*


class AddEditTaskFragment : Fragment(), AddEditTaskContract.View {
    override fun hideKeybord() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }

    override fun showKeybord() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    private fun showTimePicker() {
        class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

            override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
                // Use the current time as the default values for the picker
                val c = Calendar.getInstance()
                val hour = c.get(Calendar.HOUR_OF_DAY)
                val minute = c.get(Calendar.MINUTE)


                // Create a new instance of TimePickerDialog and return it
                return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
            }

            override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
                setLimitTime("$hourOfDay:$minute")
            }
        }

        TimePickerFragment().show((activity as FragmentActivity).fragmentManager, "TAG")
    }


    fun setLimitTime(limitTime: String) {
        limitTimeLayout.findViewById<TextView>(R.id.textView).text = limitTime
    }

    val TAG = "AddEditTaskFragment"
    lateinit var mContext: Context
    lateinit var mView: View
    lateinit var titleLayout: ConstraintLayout
    lateinit var detailLayout: ConstraintLayout
    lateinit var limitTimeLayout: ConstraintLayout
    lateinit var notificationTimeLayout: ConstraintLayout
    lateinit var weekGroupLayout: ConstraintLayout
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
        mView = view
        titleLayout = view.findViewById(R.id.edit_include_title)
        detailLayout = view.findViewById(R.id.edit_include_detail)
        limitTimeLayout = view.findViewById(R.id.edit_include_limittime)
        notificationTimeLayout = view.findViewById(R.id.edit_include_notificationtime)
        weekGroupLayout = view.findViewById(R.id.edit_include_weekgroup)

        // Buttonのセット
        saveButton = view.findViewById(R.id.edit_save_button)
        leaveButton = view.findViewById(R.id.edit_leave_button)
        // clickadapter
        saveButton.setOnClickListener {
            // 値の取得
            val title = titleLayout.findViewById<EditText>(R.id.editText).text.toString()
            val detail = detailLayout.findViewById<EditText>(R.id.editText).text.toString()
            val limitTime = limitTimeLayout.findViewById<TextView>(R.id.textView).text.toString()
            val notificationTime = notificationTimeLayout.findViewById<Spinner>(R.id.spinner).selectedItem.toString()
            val weekGroup = weekGroupLayout.findViewById<Spinner>(R.id.spinner).selectedItem.toString()

            presenter.saveTask(TaskDataModel(title = title, detail = detail, limitDate = limitTime,
                    notificationTime = notificationTime, weekGroup = weekGroup))
        }
        // OnClickのTimePicker
        limitTimeLayout.findViewById<TextView>(R.id.textView).setOnClickListener {
            showTimePicker()
        }
        leaveButton.setOnClickListener { showTasksMain() }

        presenter.start()
//        val sharedElementEnterTransition = activity?.getWindow()?.getSharedElementEnterTransition()
//        sharedElementEnterTransition?.addListener(object : TransitionListenerAdapter() {
//            override fun onTransitionEnd(transition: Transition) {
//                super.onTransitionEnd(transition)
//                Log.d(TAG,"onTransitionEnd")
//            }
//        })
    }

    override fun setTaskConfigEditRow(lists: ArrayList<AddEditTaskItemModel>) {
        for (list in lists) {
            val model = AddEditTaskItemModel
            var layout: ConstraintLayout = mView.findViewById(list.layoutid)
            layout.findViewById<ImageView>(R.id.imageView).setImageResource(list.imageId)
            if (list.componentType == model.EDITTEXT) {
                layout.findViewById<EditText>(R.id.editText).hint = list.hintText

            } else if (list.componentType == model.SPINNER) {
                // スピナーのレイアウト指定
                val adapter = ArrayAdapter<String>(mContext, R.layout.spinner_item)
                // プルダウンレイアウト指定
                adapter.setDropDownViewResource(R.layout.spinner_item)
                adapter.addAll(list.spinnerItem)
                layout.findViewById<Spinner>(R.id.spinner).adapter = adapter
            } else if (list.componentType == model.TEXTVIEW) {
                layout.findViewById<TextView>(R.id.textView).text = list.text
            }
        }

    }

    override fun showTasksMain() {
        hideKeybord()
        activity?.finish()
    }

    //    override fun onActivityCreated() {
//        super.onActivityCreated()
//        showKeybord()
//    }
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showKeybord()
    }
}
