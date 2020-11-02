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
import com.mursyidul.submissionweek8.local.pustaka.PustakaDb
import com.mursyidul.submissionweek8.viewmodel.UserViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_beranda.*
import kotlinx.android.synthetic.main.fragment_register2.*
import kotlinx.android.synthetic.main.fragment_result.*

class Register2Fragment : Fragment(),View.OnClickListener {

    private var pustakaDatabase: PustakaDb? = null

    lateinit var navController: NavController
    private lateinit var userViewModel: UserViewModel

    var ambilname: String? = null
    var ambilemail: String? = null
    var ambilpassword: String? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register2, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pustakaDatabase = context?.let { PustakaDb.getInstance(it) }

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        ambilname = arguments?.getString("name")
        ambilemail= arguments?.getString("email")
        ambilpassword= arguments?.getString("password")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnFinish.setOnClickListener(this)
        backk.setOnClickListener(this)
        tvHallo.text = "Halo $ambilname, untuk melanjutkan register silahkan isi password dibawah"

        attachObserve()

    }

    private fun attachObserve() {
        userViewModel.responseActionUser.observe(viewLifecycleOwner, Observer { isSuccessRegister() })
        userViewModel.isUserError.observe(viewLifecycleOwner, Observer { isErrorRegister(it) })
        userViewModel.isPasswordEmpty.observe(viewLifecycleOwner, Observer { isEemptyPassword() })
        userViewModel.password_less.observe(viewLifecycleOwner, Observer { isLessPassword() })
        userViewModel.passwordnotmatch.observe(viewLifecycleOwner, Observer { isPassNotMatch() })
    }

    private fun isPassNotMatch() {
        etConfirmPassword.error="Password tidak cocok"
    }

    private fun isLessPassword() {
        etPasswordd.error="Password kurang dari 5 karakter"
    }

    private fun isEemptyPassword() {
        etPasswordd.error="Password tidak boleh kosong"
    }

    private fun isErrorRegister(it: Throwable?) {
        Toast.makeText(context, "Register gagal", Toast.LENGTH_SHORT).show()
        Log.d("TAG", "errorRegister: ${it?.message}")
    }

    private fun isSuccessRegister() {

        val bundle = bundleOf(
            "password" to etPasswordd.text.toString(),
            "email" to ambilemail.toString()
        )
        navController.navigate(R.id.action_register2Fragment_to_resultFragment, bundle)
        clearFindViewByIdCache()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnFinish ->  userViewModel.registerUser(
                null,
                ambilname.toString(),
                ambilemail.toString(),
                etPasswordd.text.toString(),
                etConfirmPassword.text.toString()

            )
            R.id.backk -> navController.navigate(R.id.register1Fragment)


        }
    }


}