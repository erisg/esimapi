package com.geral.esimapi

import android.app.Service
import android.content.Intent
import android.os.IBinder

class CarrierEuiccProvisioningService : Service() {
    var binder = object : ICarrierEuiccProvisioningService.Stub() {
        override fun getActivationCode(callback: IGetActivationCodeCallback?) {
            callback?.onSuccess("1" + "$" + "prod.smdp-plus.rsp.goog$9RS2-4AT0-MPKU-HKUO")
        }

        override fun getActivationCodeForEid(eid: String?, callback: IGetActivationCodeCallback?) {
            callback?.onSuccess("1" + "$" + "prod.smdp-plus.rsp.goog$9RS2-4AT0-MPKU-HKUO")
        }
    }
    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
}
