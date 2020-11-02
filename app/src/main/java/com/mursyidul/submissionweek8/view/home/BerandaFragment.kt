package com.mursyidul.submissionweek8.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mursyidul.submissionweek8.R
import com.mursyidul.submissionweek8.local.pustaka.PustakaDb
import com.mursyidul.submissionweek8.viewmodel.UserViewModel

class BerandaFragment : Fragment() {

    private var pustakaDb: PustakaDb? = null
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pustakaDb = context?.let { PustakaDb.getInstance(it) }
        userViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
    }

}