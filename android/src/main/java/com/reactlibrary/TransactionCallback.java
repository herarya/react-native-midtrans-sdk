package com.reactlibrary;

import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;

/**
 * Created by AlSahfer on 23/11/17.
 */


public class TransactionCallback implements TransactionFinishedCallback {
    Callback callback;

    TransactionCallback(Callback callback){
        this.callback = callback;
    }

    @Override
    public void onTransactionFinished(TransactionResult transactionResult) {
        if(transactionResult.getResponse() != null){
            Log.d("trans success", transactionResult.getStatus());
            WritableMap result = Arguments.createMap();
            WritableMap response = Arguments.createMap();
            response.putString("transaction_time",transactionResult.getResponse().getTransactionTime());
            response.putString("transaction_status",transactionResult.getResponse().getTransactionStatus());
            response.putString("transaction_id",transactionResult.getResponse().getTransactionId());
            response.putString("status_message",transactionResult.getResponse().getStatusMessage());
            response.putString("status_code",transactionResult.getResponse().getStatusCode());
            response.putString("payment_type",transactionResult.getResponse().getPaymentType());
            response.putString("order_id",transactionResult.getResponse().getOrderId());
            response.putString("gross_amount",transactionResult.getResponse().getGrossAmount());
            response.putString("fraud_status",transactionResult.getResponse().getFraudStatus());
            response.putString("currency",transactionResult.getResponse().getCurrency());
            result.putMap("response",response);
            result.putString("status",transactionResult.getStatus());
            this.callback.invoke(result);
        }else if(transactionResult.isTransactionCanceled()){
            Log.d("trans canceled", "Payment Canceled");
        }else{
            if (transactionResult.getStatus().equalsIgnoreCase(TransactionResult.STATUS_INVALID)) {
                Log.d("trans canceled", "\"Transaction Invalid");
            } else {
                Log.d("trans canceled", "\"Transaction Finished with failure");
            }
        }

    }
}
