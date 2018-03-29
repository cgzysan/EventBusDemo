package ysan.eventbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ysan.eventbusdemo.activity.BroadcastActivity;
import ysan.eventbusdemo.activity.EventBusActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btBroad = findViewById(R.id.bt_broadcast);
        Button btEventbus = findViewById(R.id.bt_eventbus);
        btBroad.setOnClickListener(this);
        btEventbus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_broadcast:
                Intent broadIntent = new Intent(this, BroadcastActivity.class);
                startActivity(broadIntent);
                break;
            case R.id.bt_eventbus:
                Intent eventbusIntent = new Intent(this, EventBusActivity.class);
                startActivity(eventbusIntent);
                break;
        }
    }
}
