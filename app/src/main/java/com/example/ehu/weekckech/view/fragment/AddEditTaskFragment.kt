package com.example.ehu.weekckech.view.fragment


import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.AddEditTaskItemModel
import com.example.ehu.weekckech.data.sql.TaskDataModel
import com.example.ehu.weekckech.databinding.FragmentAddEditTaskBinding
import com.example.ehu.weekckech.presenter.contract.AddEditTaskContract
import com.example.ehu.weekckech.presenter.presenter.AddEditTaskPresenter
import java.util.*


class AddEditTaskFragment : Fragment(), AddEditTaskContract.View {
    val TAG = "AddEditTaskFragment"
    lateinit var mContext: Context
    lateinit var mView: View
    lateinit var binding: FragmentAddEditTaskBinding
    override var presenter: AddEditTaskContract.Presenter = AddEditTaskPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_edit_task, container, false)
        binding.presenter = presenter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Contextの格納
        mContext = view.context
        mView = view
        // clickadapter
        binding.editSaveButton.setOnClickListener {
            // 値の取得
            val detail = binding.editIncludeDetail.editText.text.toString()
            val limitTime = binding.editIncludeLimittime.textView.text.toString()
            val notificationTime = binding.editIncludeNotificationtime.spinner.selectedItem.toString()
            val weekGroup = binding.editIncludeWeekgroup.spinner.selectedItem.toString()

            presenter.saveTask(TaskDataModel(detail = detail, limitDate = limitTime,
                    notificationTime = notificationTime, weekGroup = weekGroup))
        }
        // OnClickのTimePicker
        binding.editIncludeLimittime.textView.setOnClickListener {
            showTimePicker()
        }
        binding.editLeaveButton.setOnClickListener { showTasksMain() }
        binding.nextItem.setOnClickListener { /*TODO Click処理*/ }
        binding.prevItem.setOnClickListener { /*TODO Click処理*/ }
        presenter.start()
    }

    override fun setTaskConfigEditRow(listItemModel: ArrayList<AddEditTaskItemModel>) {
        for (list in listItemModel) {
            val model = AddEditTaskItemModel
            val layout: ConstraintLayout = mView.findViewById(list.layoutid)
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

    override fun onDestroy() {
        super.onDestroy()
        hideKeybord()
    }

    override fun onPause() {
        super.onPause()
        hideKeybord()
    }

    override fun onResume() {
        super.onResume()
        showKeybord()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showKeybord()
    }

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
        TimePickerFragment().show((activity as FragmentActivity).supportFragmentManager, "TAG")
    }

    fun setLimitTime(limitTime: String) {
        binding.editIncludeLimittime.textView.text = limitTime
    }

}
