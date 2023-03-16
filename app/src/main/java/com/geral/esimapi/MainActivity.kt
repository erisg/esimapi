package com.geral.esimapi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.euicc.DownloadableSubscription
import android.telephony.euicc.EuiccInfo
import android.telephony.euicc.EuiccManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geral.esimapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val ACTION_DOWNLOAD_SUBSCRIPTION = "download_subscription"
    val LPA_DECLARED_PERMISSION = "com.your.company.lpa.permission.BROADCAST"
    var action = "android.service.euicc.action.START_EUICC_ACTIVATION"
    var code = "1" + "$" + "prod.smdp-plus.rsp.goog$052X-UFXS-CQIY-PNGL"
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val euiccManager: EuiccManager =
            this.getSystemService(Context.EUICC_SERVICE) as EuiccManager

        Toast.makeText(this, "${euiccManager.isEnabled}", Toast.LENGTH_SHORT).show()
        // hardware eUICC y la versión del sistema operativo eSIM
        val info: EuiccInfo? = euiccManager.euiccInfo
        val osVer = info?.osVersion

        binding.button.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    fun openLPA() {
        val intent: Intent = Intent(action)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("po", "${data?.data}")
    }
    /*
    fun qr() {
        val euiccManager: EuiccManager =
            this.getSystemService(Context.EUICC_SERVICE) as EuiccManager
        var resultIntent: Intent
        val receiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                var detailedCode = 0
                if (action != intent.action) {
                    return
                }
                resultCode = resultCode
                detailedCode = intent.getIntExtra(
                    EuiccManager.EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE,
                    0 /* defaultValue*/
                )
                 resultIntent = intent
            }
        }

        this.registerReceiver(
            receiver,
            IntentFilter(ACTION_DOWNLOAD_SUBSCRIPTION),
            LPA_DECLARED_PERMISSION /* broadcastPermission*/,
            null /* handler */
        )

        val sub = DownloadableSubscription
            .forActivationCode(code /* encodedActivationCode*/)
        val intent = Intent(action)

        val callbackIntent = PendingIntent.getBroadcast(
            this,
            0 /* requestCode */,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        euiccManager.downloadSubscription(
            sub,
            true /* switchAfterDownload */,
            callbackIntent
        )
    }

     */
}
