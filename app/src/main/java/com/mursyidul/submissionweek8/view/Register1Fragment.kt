package com.mursyidul.submissionweek8.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.mursyidul.submissionweek8.R
import com.mursyidul.submissionweek8.viewmodel.UserViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_register1.*

class Register1Fragment : Fragment(),View.OnClickListener{

    lateinit var navController: NavController
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register1, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnNext.setOnClickListener(this)
        bback.setOnClickListener(this)

        attachObserve()

    }

    private fun attachObserve() {
        userViewModel.responseActionUser.observe(viewLifecycleOwner, Observer { isGetUser() })
        userViewModel.isUserError.observe(viewLifecycleOwner, Observer { isEmptyUser(it) })
        userViewModel.wrong_email.observe(viewLifecycleOwner, Observer { isWrongEmail() })
        userViewModel.empty_email.observe(viewLifecycleOwner, Observer { isEmptyEmail() })
        userViewModel.empty_name.observe(viewLifecycleOwner, Observer { isEmptyName() })
    }

    private fun isEmptyName() {
        etName.error ="nama tidak boleh kosong"
    }

    private fun isEmptyEmail() {
        etEmaill.error = "email tidak boleh kosong"
    }

    private fun isWrongEmail() {
        etEmaill.error = "emamil tidak valid"
    }

    private fun isEmptyUser(it: Throwable?) {
        Log.d("TAG", "Lanjut register, email belum ada")
        val bundle = bundleOf(
            "name" to etName.text.toString(),
            "email" to etEmaill.text.toString()
        )
        navController.navigate(
            R.id.action_register1Fragment_to_register2Fragment,
            bundle
        )
        clearFindViewByIdCache()


    }

    private fun isGetUser() {
        Log.d("TAG", "email sudah ada")
        Toast.makeText(context, "Email sudah terdaftar", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnNext ->{
                userViewModel.getEmail(etEmaill.text.toString(),etName.text.toString())

            }
            R.id.bback-> activity?.onBackPressed()
        }
    }


}