package com.example.lab05_3;

import android.content.*;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.ArrayList;

public class SmsReceiver extends BroadcastReceiver {

    public static final String SMS_FORWARD_BROADCAST_RECEIVER = "sms_forward_broadcast_receiver";
    public static final String SMS_MESSAGE_ADDRESS_KEY = "sms_messages_key";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (!"android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())) return;

        String keyword = context.getString(R.string.are_you_ok).toLowerCase();
        Bundle bundle = intent.getExtras();

        if (bundle == null) return;

        Object[] pdus = (Object[]) bundle.get("pdus");
        String format = bundle.getString("format");

        if (pdus == null) return;

        ArrayList<String> addresses = new ArrayList<>();

        for (Object pdu : pdus) {
            SmsMessage msg;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                msg = SmsMessage.createFromPdu((byte[]) pdu, format);
            } else {
                msg = SmsMessage.createFromPdu((byte[]) pdu);
            }

            if (msg.getMessageBody().toLowerCase().contains(keyword)) {
                addresses.add(msg.getOriginatingAddress());
            }
        }

        if (addresses.isEmpty()) return;

        if (!MainActivity.isRunning) {
            Intent i = new Intent(context, MainActivity.class);
            i.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Intent forward = new Intent(SMS_FORWARD_BROADCAST_RECEIVER);
            forward.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
            forward.setPackage(context.getPackageName());
            context.sendBroadcast(forward);
        }
    }
}
