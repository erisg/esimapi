package com.geral.esimapi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.euicc.EuiccInfo
import android.telephony.euicc.EuiccManager
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val ACTION_DOWNLOAD_SUBSCRIPTION = "download_subscription"
    val LPA_DECLARED_PERMISSION = "com.your.company.lpa.permission.BROADCAST"
    var action = "android.service.euicc.action.START_CARRIER_ACTIVATION"
    var code = "1" + "$" + "prod.smdp-plus.rsp.goog$052X-UFXS-CQIY-PNGL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val euiccManager: EuiccManager =
            this.getSystemService(Context.EUICC_SERVICE) as EuiccManager

        // es compatible
        euiccManager.isEnabled
        // hardware eUICC y la versión del sistema operativo eSIM
        val info: EuiccInfo? = euiccManager.euiccInfo
        val osVer = info?.osVersion
    }

    fun flow() {
        val packageName = "com.geral.esimapi"
        val carrierAppIntent = Intent(action).setPackage(packageName)

        val activity = this.packageManager.resolveActivity(carrierAppIntent, 0)

        if (activity != null) {
            carrierAppIntent
                .setClassName(activity.activityInfo.packageName, activity.activityInfo.name)
        }

        startActivityForResult(carrierAppIntent, 0)
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        intent.data
    }
}
