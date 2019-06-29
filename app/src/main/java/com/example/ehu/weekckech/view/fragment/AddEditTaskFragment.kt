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
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.runBlocking
import java.util.*


class AddEditTaskFragment : Fragment(), AddEditTaskContract.View {
    val TAG = "AddEditTaskFragment"
    lateinit var mContext: Context
    lateinit var mView: View
    lateinit var binding: FragmentAddEditTaskBinding
    override var presenter: AddEditTaskContract.Presenter = AddEditTaskPresenter(this)
    private val chips = mutableListOf<Chip>()

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
        // save
        binding.editSaveButton.setOnClickListener {
            // 値の取得
            // detail
            val detail = binding.editIncludeDetail.editText.text.toString()
            // LimitTime
            val limitTime = binding.editIncludeLimittime.textView.text.toString()
            // WeekGroup
            var weekGroup = "月"
            for (i in 0 until binding.editIncludeWeekgroup.spinner.childCount) {
                val chip = binding.editIncludeWeekgroup.spinner.getChildAt(i) as Chip
                if (chip.isChecked) {
                    weekGroup = chip.text.toString()
                    break
                }
            }
            // NotificationTime
            val notificationTime = arrayListOf<String>()
            for (i in 0 until binding.editIncludeNotificationtime.chipGroup.childCount) {
                val chip = binding.editIncludeNotificationtime.chipGroup.getChildAt(i) as Chip
                if (chip.isChecked) notificationTime.add(chip.text.toString())
            }

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
        model?.let {
            presenter.loadTaskConfigEditRow(it)
        }
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
        for (i in 0 until binding.editIncludeWeekgroup.spinner.childCount) {
            val chip = binding.editIncludeWeekgroup.spinner.getChildAt(i) as Chip
            // model.weekGroupと一致する曜日のみをCheckする
            chip.isChecked = chip.text.toString() == model.weekGroup
        }
        // NotificationTime
        model.notificationTime?.forEach {
            onAddChip(it, isShowToast = false, isAllSelected = true)
        }
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
                onAddChip(text, true)
            }
        }
        TimePickerFragment().show((activity as FragmentActivity).supportFragmentManager, "TAG")
    }

    override fun setTaskConfigInit() {
        binding.editIncludeWeekgroup.mon.isChecked = true // 初期セットは「月」
    }

    private fun setLimitTime(limitTime: String) {
        binding.editIncludeLimittime.textView.text = limitTime
    }

    private fun onAddChip(text: String, isShowToast: Boolean, isAllSelected: Boolean = false) {
        val scrollView = binding.editIncludeNotificationtime.scrollView
        val initText = binding.editIncludeNotificationtime.initText
        val chipGroup = binding.editIncludeNotificationtime.chipGroup
        // initTextが表示されていたら非表示にして、ScrollViewを表示する
        if (initText.visibility == View.VISIBLE) {
            initText.visibility = View.GONE
            scrollView.visibility = View.VISIBLE
        }
        chips.forEach {
            if (it.isCloseIconVisible) it.isCloseIconVisible = false
        }

        chipGroup.cancelLongPress()
        chipGroup.addView(setUpChip(chipGroup, text, isAllSelected))
        if (isShowToast) Toast.makeText(context, "「$text」が追加されました。\n「$text」をタップするとその時間に通知します。", Toast.LENGTH_LONG).show()
    }

    private fun setUpChip(chipGroup: ChipGroup, text: String, isAllSelected: Boolean): Chip {
        // Chipを追加
        val chip = Chip(context)
        chips.add(chip)
        chip.text = text
        chip.isCheckable = true
        chip.isCheckedIconVisible = false
        chip.isChecked = isAllSelected
        // LongClickでCloseIconを表示
        chip.setOnLongClickListener {
            chips.forEach {
                it.isCloseIconVisible = !it.isCloseIconVisible
            }
            true
        }
        // CloseIconでChipを削除
        chip.setOnCloseIconClickListener {
            onCloseChip(chipGroup, chip)
        }
        return chip
    }

    private fun onCloseChip(chipGroup: ChipGroup, chip: Chip) {
        chipGroup.removeView(chip)
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
