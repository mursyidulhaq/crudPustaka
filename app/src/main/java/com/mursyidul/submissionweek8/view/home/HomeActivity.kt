package com.mursyidul.submissionweek8.view.home

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.mursyidul.submissionweek8.R
import com.mursyidul.submissionweek8.helper.SessionManager
import com.mursyidul.submissionweek8.view.MainActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navController  = Navigation.findNavController(this,R.id.home_nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.login_options,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        if(id==R.id.logout){
            val session = SessionManager(this)
            AlertDialog.Builder(this).apply {
                setTitle("Keluar")
                setMessage("Yakin ingin keluar ?")
                setCancelable(false)
                setPositiveButton("Yakin") { dialogInterface, i ->
                    session.logout()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                }
                setNegativeButton("Batal") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
            }.show()

        }
        return super.onOptionsItemSelected(item)
    }
}