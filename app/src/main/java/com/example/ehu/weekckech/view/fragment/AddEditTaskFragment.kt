package com.example.ehu.weekckech.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ehu.weekckech.R
import com.example.ehu.weekckech.presenter.contract.AddEditTaskContract
import com.example.ehu.weekckech.presenter.presenter.AddEditTaskPresenter


class AddEditTaskFragment : Fragment() ,AddEditTaskContract.View{
    override var presenter: AddEditTaskContract.Presenter = AddEditTaskPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_edit_task, container, false)
    }

    override fun showTaskConfigEditRow() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTasksMain() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
