package com.example.lab05_3;

import android.Manifest;
import android.content.*;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {

    public static boolean isRunning = false;

    private ReentrantLock lock;
    private Switch swAuto;
    private Button btnSafe, btnMayday;
    private ArrayList<String> requesters;
    private ArrayAdapter<String> adapter;
    private ListView lv;
    private BroadcastReceiver forwardReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swAuto = findViewById(R.id.sw_auto_response);
        btnSafe = findViewById(R.id.btn_safe);
        btnMayday = findViewById(R.id.btn_mayday);
        lv = findViewById(R.id.lv_messages);

        lock = new ReentrantLock();
        requesters = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, requesters);
        lv.setAdapter(adapter);

        // ✅ Xin quyền
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS},
                1);

        setupUI();
        initForwardReceiver();
        getDataFromIntent();
    }

    private void setupUI() {
        btnSafe.setOnClickListener(v -> respond(true));
        btnMayday.setOnClickListener(v -> respond(false));

        swAuto.setOnCheckedChangeListener((buttonView, isChecked) -> {
            btnSafe.setVisibility(isChecked ? View.GONE : View.VISIBLE);
            btnMayday.setVisibility(isChecked ? View.GONE : View.VISIBLE);
        });
    }

    private void respond(boolean ok) {
        String text = ok ? getString(R.string.i_am_safe_and_well) : getString(R.string.tell_my_mother_i_love_her);

        ArrayList<String> list = new ArrayList<>(requesters);
        SmsManager sms = SmsManager.getDefault();

        for (String number : list) {
            sms.sendTextMessage(number, null, text, null, null);
            requesters.remove(number);
        }

        adapter.notifyDataSetChanged();
    }

    private void initForwardReceiver() {
        forwardReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ArrayList<String> arr = intent.getStringArrayListExtra(SmsReceiver.SMS_MESSAGE_ADDRESS_KEY);
                if (arr != null) {
                    processAddresses(arr);
                }
            }
        };
    }

    private void processAddresses(ArrayList<String> arr) {
        for (String number : arr) {
            if (!requesters.contains(number)) {
                requesters.add(number);
            }
        }
        adapter.notifyDataSetChanged();

        if (swAuto.isChecked()) {
            respond(true);
        }
    }

    private void getDataFromIntent() {
        Intent i = getIntent();
        if (i.hasExtra(SmsReceiver.SMS_MESSAGE_ADDRESS_KEY)) {
            processAddresses(i.getStringArrayListExtra(SmsReceiver.SMS_MESSAGE_ADDRESS_KEY));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;

        registerReceiver(forwardReceiver,
                new IntentFilter(SmsReceiver.SMS_FORWARD_BROADCAST_RECEIVER),
                Context.RECEIVER_NOT_EXPORTED);
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
        unregisterReceiver(forwardReceiver);
    }
}