// ICarrierEuiccProvisioningService.aidl
package com.geral.esimapi;

// Declare any non-default types here with import statements

interface ICarrierEuiccProvisioningService {
     // The method to get the activation code from the carrier app. The caller needs to pass in
       // the implementation of IGetActivationCodeCallback as the parameter.
       void getActivationCode(in IGetActivationCodeCallback callback);

       // The method to get the activation code from the carrier app. The caller needs to pass in
       // the activation code string as the first parameter and the implementation of
       // IGetActivationCodeCallback as the second parameter. This method provides the carrier
       // app the device EID which allows a carrier to pre-bind a profile to the device's EID before
       // the download process begins.
       void getActivationCodeForEid(in String eid, in IGetActivationCodeCallback callback);
}