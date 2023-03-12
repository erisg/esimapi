// IGetActivationCodeCallback.aidl
package com.geral.esimapi;

// Declare any non-default types here with import statements

interface IGetActivationCodeCallback {
     // The call back method needs to be called when the carrier app gets the activation
       // code successfully. The caller needs to pass in the activation code string as the
       // parameter.
       void onSuccess(String activationCode);

       // The call back method needs to be called when the carrier app failed to get the
       // activation code.
       void onFailure();
}