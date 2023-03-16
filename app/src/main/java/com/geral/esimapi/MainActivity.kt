package com.geral.esimapi

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.euicc.DownloadableSubscription
import android.telephony.euicc.EuiccInfo
import android.telephony.euicc.EuiccManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
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

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val euiccManager: EuiccManager =
            this.getSystemService(Context.EUICC_SERVICE) as EuiccManager

        // es compatible
        val activationCode = "1234-5678-9012-3456" // código de activación de la eSIM
        val confirmationCode = "123456" // código de confirmación de la eSIM

        val request = DownloadableSubscription.Builder(activationCode)
            .setEncodedActivationCode(activationCode)
            .setConfirmationCode(confirmationCode)
            .build()

        val intent = Intent(action).setPackage(applicationContext.packageName)
        val act = applicationContext.packageManager.resolveActivity(intent, 0)
        intent.setClassName(
            act?.activityInfo?.packageName.orEmpty(),
            act?.activityInfo?.name.orEmpty()
        )

        Toast.makeText(this, "${euiccManager.isEnabled}", Toast.LENGTH_SHORT).show()
        // hardware eUICC y la versión del sistema operativo eSIM
        val info: EuiccInfo? = euiccManager.euiccInfo
        val osVer = info?.osVersion

        binding.button.setOnClickListener {
            euiccManager.downloadSubscription(
                request,
                true,
                PendingIntent.getService(
                    this,
                    1,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            )
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
