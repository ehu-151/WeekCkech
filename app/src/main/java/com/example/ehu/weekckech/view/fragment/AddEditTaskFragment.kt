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
import com.example.ehu.weekckech.data.sql.AddEditTaskItemModel
import com.example.ehu.weekckech.data.sql.TaskDataModel
import com.example.ehu.weekckech.databinding.FragmentAddEditTaskBinding
import com.example.ehu.weekckech.presenter.activity.AddEditTaskActivity
import com.example.ehu.weekckech.presenter.contract.AddEditTaskContract
import com.example.ehu.weekckech.presenter.presenter.AddEditTaskPresenter
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
            val notificationTime = binding.editIncludeNotificationtime.spinner.selectedItem.toString()
            val weekGroup = when (binding.editIncludeWeekgroup.spinner.selectedItem.toString()) {
                "日" -> 0
                "月" -> 1
                "火" -> 2
                "水" -> 3
                "木" -> 4
                "金" -> 5
                "土" -> 6
                else -> 0
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
        binding.editLeaveButton.setOnClickListener { showTasksMain() }

        binding.nextItem.setOnClickListener { /*TODO Click処理*/ }
        binding.prevItem.setOnClickListener { /*TODO Click処理*/ }

        // 初期テキストのセット
        presenter.loadTaskConfigEditRow(model)
        presenter.start()
    }

    override fun setTaskConfigEditRow(listItemModel: ArrayList<AddEditTaskItemModel>) {
        for (list in listItemModel) {
            val model = AddEditTaskItemModel
            val layout: ConstraintLayout = mView.findViewById(list.layoutid)
            layout.findViewById<ImageView>(com.example.ehu.weekckech.R.id.imageView).setImageResource(list.imageId)
            if (list.componentType == model.EDITTEXT) {
                layout.findViewById<EditText>(com.example.ehu.weekckech.R.id.editText).hint = list.hintText
                layout.findViewById<EditText>(com.example.ehu.weekckech.R.id.editText).setText(list.text)

            } else if (list.componentType == model.SPINNER) {
                // スピナーのレイアウト指定
                val adapter = ArrayAdapter<String>(mContext, com.example.ehu.weekckech.R.layout.spinner_item)
                // プルダウンレイアウト指定
                adapter.setDropDownViewResource(com.example.ehu.weekckech.R.layout.spinner_item)
                adapter.addAll(list.spinnerItem)
                val spinner = layout.findViewById<Spinner>(com.example.ehu.weekckech.R.id.spinner)
                spinner.adapter = adapter
                spinner.setSelection(list.spinnerItem!!.indexOf(list.selection))
            } else if (list.componentType == model.TEXTVIEW) {
                layout.findViewById<TextView>(com.example.ehu.weekckech.R.id.textView).text = list.text
            }
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

    private fun setLimitTime(limitTime: String) {
        binding.editIncludeLimittime.textView.text = limitTime
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
