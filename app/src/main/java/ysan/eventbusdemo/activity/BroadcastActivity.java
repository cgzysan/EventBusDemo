package ysan.eventbusdemo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import ysan.eventbusdemo.R;
import ysan.eventbusdemo.service.BroadService;

/**
 * Created by YSAN on 2018/03/29
 */

public class BroadcastActivity extends AppCompatActivity {

    private TextView mTvMsg;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case "TIMEOUT":
                    Log.i("ysan", "Current thread = " + Thread.currentThread().getName());
                    String hello = intent.getStringExtra("hello");
                    mTvMsg.setText(hello);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mTvMsg = findViewById(R.id.tv_message);
        Intent timeService = new Intent(this, BroadService.class);
        startService(timeService);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter("TIMEOUT");
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }
}
