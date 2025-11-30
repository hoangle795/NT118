package com.example.lab5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContent = findViewById(R.id.tv_content);
        initBroadcastReceiver();
    }

    // Tạo BroadcastReceiver lắng nghe SMS
    private void initBroadcastReceiver() {

        filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Bundle bundle = intent.getExtras();
                if (bundle == null) return;

                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus == null || pdus.length == 0) return;

                StringBuilder msgBody = new StringBuilder();
                String sender = "";

                for (Object pdu : pdus) {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
                    sender = sms.getDisplayOriginatingAddress();
                    msgBody.append(sms.getMessageBody());
                }

                // Hiển thị toast khi có tin nhắn mới
                Toast.makeText(context,
                        getString(R.string.you_have_a_new_message),
                        Toast.LENGTH_SHORT).show();

                tvContent.setText("From: " + sender + "\nMessage: " + msgBody);
            }
        };
    }

    // Tự động đăng ký khi Activity Resume
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, filter);
    }

    // Tự động huỷ đăng ký khi Activity Stop
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }
}
