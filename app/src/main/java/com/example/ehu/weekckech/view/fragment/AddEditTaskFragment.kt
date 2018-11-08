package com.example.ehu.weekckech.view.fragment


import android.app.Dialog
import android.app.DialogFragment
import android.app.TimePickerDialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.data.sql.AddEditTaskItemModel
import com.example.ehu.weekckech.databinding.FragmentAddEditTaskBinding
import com.example.ehu.weekckech.presenter.adapter.AddEditTasksListitemAdapter
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

    override fun showTimePicker() {
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
    lateinit var limitTimeLayout: ConstraintLayout
    override var presenter: AddEditTaskContract.Presenter = AddEditTaskPresenter(this)
    lateinit var binding:FragmentAddEditTaskBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_add_edit_task,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Contextの格納
        mContext = view.context
        mView = view



        // OnClickのTimePicker
//        limitTimeLayout.findViewById<TextView>(R.id.textView).setOnClickListener {
//            showTimePicker()
//        }
        // やめるボタン
        binding.editLeaveButton.setOnClickListener { showTasksMain() }

        presenter.start()
    }

    override fun setTaskConfigEditRow(lists: ArrayList<AddEditTaskItemModel>) {
        binding.listview.adapter= AddEditTasksListitemAdapter(mContext,lists,presenter)
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
}
