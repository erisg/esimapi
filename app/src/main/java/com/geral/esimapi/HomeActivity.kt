package com.geral.esimapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class HomeActivity : AppCompatActivity() {

    var action = "android.service.euicc.action.START_EUICC_ACTIVATION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        openLPA()
    }

    fun openLPA() {

        val intent = Intent(action).setPackage(applicationContext.packageName)
        val act = applicationContext.packageManager.resolveActivity(intent, 0)
        intent.setClassName(
            act?.activityInfo?.packageName.orEmpty(),
            act?.activityInfo?.name.orEmpty()
        )
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("po", "${data?.data}")
    }
}