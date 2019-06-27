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
import android.widget.ArrayAdapter
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.TaskDataModel
import com.example.ehu.weekckech.databinding.FragmentAddEditTaskBinding
import com.example.ehu.weekckech.presenter.activity.AddEditTaskActivity
import com.example.ehu.weekckech.presenter.contract.AddEditTaskContract
import com.example.ehu.weekckech.presenter.presenter.AddEditTaskPresenter
import com.google.android.material.chip.Chip
import kotlinx.coroutines.runBlocking
import java.util.*


class AddEditTaskFragment : Fragment(), AddEditTaskContract.View {
    val TAG = "AddEditTaskFragment"
    lateinit var mContext: Context
    lateinit var mView: View
    lateinit var binding: FragmentAddEditTaskBinding
    override var presenter: AddEditTaskContract.Presenter = AddEditTaskPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, com.example.ehu.weekckech.R.layout.fragment_add_edit_task, container, false)
        binding.presenter = presenter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // argsの取得
        val args = arguments
        val model = args?.getSerializable(AddEditTaskActivity().EXTRA_TASK_ID) as TaskDataModel?

        // Contextの格納
        mContext = view.context
        mView = view
        // clickadapter
        binding.editSaveButton.setOnClickListener {
            // 値の取得
            val detail = binding.editIncludeDetail.editText.text.toString()
            val limitTime = binding.editIncludeLimittime.textView.text.toString()
            // TODO:後で直す
//            val notificationTime = binding.editIncludeNotificationtime.textView.text.toString()
            val notificationTime = "30分前"
            val weekGroup = binding.editIncludeWeekgroup.spinner.selectedItem.toString()

            // 詳細が記入されているなら、save
            if (detail != "") {
                presenter.saveTask(TaskDataModel(taskId = model?.taskId, detail = detail, limitTime = limitTime,
                        notificationTime = notificationTime, weekGroup = weekGroup), mContext)
            } else {
                Toast.makeText(mContext, "何も書かれていません。", Toast.LENGTH_SHORT).show()
            }

        }
        // OnClickのTimePicker
        binding.editIncludeLimittime.textView.setOnClickListener {
            showTimePicker()
        }
        binding.editIncludeNotificationtime.addNotification.setOnClickListener {
            showHourMuniteTimePicker()
        }
        binding.editLeaveButton.setOnClickListener { showTasksMain() }

        binding.nextItem.setOnClickListener { /*TODO Click処理*/ }
        binding.prevItem.setOnClickListener { /*TODO Click処理*/ }

        // 初期テキストのセット
        presenter.loadTaskConfigEditRow(model)
        presenter.start()
    }

    override fun setTaskConfigEditRow(model: TaskDataModel) {
        // Detail
        binding.editIncludeDetail.editText.apply {
            setText(model.detail)
        }
        // LimitTime
        binding.editIncludeLimittime.textView.apply {
            text = model.limitTime
        }
        // WeekGroup
        val itemAdapter = ArrayAdapter<String>(mContext, R.layout.spinner_item)
        val array = resources.getStringArray(R.array.edit_weekgroup_init)
        array.forEach { itemAdapter.add(it) }
        binding.editIncludeWeekgroup.spinner.apply {
            adapter = itemAdapter
            setSelection(array.indexOf(model.weekGroup))
        }
        // TODO:NotificationTimeもビューに描画する
    }

    override fun showTasksMain() {
        activity?.finish()
    }

    override fun onResume() {
        super.onResume()
        //ホームから復帰した時
        showKeyboard()
    }

    override fun showKeyboard() {
        // 100だと、キーボードが表示されない
        runBlocking {
            binding.editIncludeDetail.editText.postDelayed(Runnable {
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                binding.editIncludeDetail.editText.requestFocus()
                imm.showSoftInput(binding.editIncludeDetail.editText, 0)
            }, 200)
        }
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

    private fun showHourMuniteTimePicker() {
        class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
            override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
                // Use the current time as the default values for the picker
                val hour = 0
                val minute = 0

                // Create a new instance of TimePickerDialog and return it
                return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
            }

            override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
                val text = if (hourOfDay == 0 && minute == 0) {
                    Toast.makeText(context, R.string.notification_warning_null, Toast.LENGTH_SHORT).show()
                    return
                } else if (hourOfDay == 0) {
                    "${minute}分前"
                } else if (minute == 0) {
                    "${hourOfDay}時間前"
                } else {
                    "${hourOfDay}時間${minute}分前"
                }
                onAddChip(text)
            }
        }
        TimePickerFragment().show((activity as FragmentActivity).supportFragmentManager, "TAG")
    }

    private fun setLimitTime(limitTime: String) {
        binding.editIncludeLimittime.textView.text = limitTime
    }

    private fun onAddChip(text: String) {
        val scrollView = binding.editIncludeNotificationtime.scrollView
        val initText = binding.editIncludeNotificationtime.initText
        // initTextが表示されていたら非表示にして、ScrollViewを表示する
        if (initText.visibility == View.VISIBLE) {
            initText.visibility = View.GONE
            scrollView.visibility = View.VISIBLE
        }
        // Chipを追加
        val chip = Chip(context)
        chip.text = text
        chip.isCheckable = true
        binding.editIncludeNotificationtime.chipGroup.addView(chip)
    }

    companion object {
        fun newInstance(model: TaskDataModel?): AddEditTaskFragment {
            val fragment = AddEditTaskFragment()
            val args = Bundle()
            args.putSerializable(AddEditTaskActivity().EXTRA_TASK_ID, model)
            fragment.arguments = args
            return fragment
        }
    }

}
