package com.mursyidul.submissionweek8.view

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
import com.mursyidul.submissionweek8.local.user.User
import com.mursyidul.submissionweek8.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(),View.OnClickListener {

    lateinit var navController: NavController
    private lateinit var userViewModel: UserViewModel

    var ambilemail: String? = null
    var ambilpassword: String? = null
    var ambilname:String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnRegister.setOnClickListener(this)
        btnLogin.setOnClickListener(this)
        attachObserve()
    }

    private fun attachObserve() {
        userViewModel.responseActionUser.observe(viewLifecycleOwner, Observer { isLoginSuccess(it) })
        userViewModel.isUserError.observe(viewLifecycleOwner, Observer { isLoginError(it) })
        userViewModel.empty_email.observe(viewLifecycleOwner, Observer { isEmailEmpty() })
        userViewModel.isPasswordEmpty.observe(viewLifecycleOwner, Observer { isPasswordEmpty() })

    }

    private fun isPasswordEmpty() {
        etPassword.error =  "email tidak boleh kosong"

    }

    private fun isEmailEmpty() {
        etEmail.error =  "email tidak boleh kosong"
    }

    private fun isLoginError(it: Throwable?) {

        Toast.makeText(context,"Login Failed, Email belum terdaftar atau password salah", Toast.LENGTH_SHORT).show()
        Log.d("TAG", "errorLogin: ${it?.message}")


    }

    private fun isLoginSuccess(it: User?) {

        Log.d("TAG", "loginSuccess: OK")
        val bundle= bundleOf(
            "password" to etPassword.text.toString(),
            "email" to etEmail.text.toString()
        )
        navController.navigate(R.id.action_mainFragment_to_homeActivity,bundle)
        activity?.finish()


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        ambilemail = arguments?.getString("email")
        ambilpassword = arguments?.getString("password")
        ambilname = arguments?.getString("name")

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnRegister->navController.navigate(R.id.action_mainFragment_to_register1Fragment)

            R.id.btnLogin->
                userViewModel.loginUser(etEmail.text.toString(),etPassword.text.toString())
        }
    }


}