package com.mursyidul.submissionweek8.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.mursyidul.submissionweek8.R
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : Fragment(),View.OnClickListener {
    lateinit var navController: NavController

    //var ambilname : String? = null
    var ambilemail : String? = null
    var ambilpassword:String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ambilemail = arguments?.getString("email")
        //ambilname = arguments?.getString("name")
        ambilpassword = arguments?.getString("password")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnBackToLogin.setOnClickListener(this)
        back.setOnClickListener(this)

        etNamee.text = ambilemail
        etEmaile.text = ambilpassword

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnBackToLogin->{
                navController.navigate(R.id.mainFragment)

            }
            R.id.back->activity?.onBackPressed()

        }
    }


}