package ysan.eventbusdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import ysan.eventbusdemo.R;
import ysan.eventbusdemo.bean.Message;
import ysan.eventbusdemo.bean.News;
import ysan.eventbusdemo.service.EventbusService;
import ysan.eventbusdemo.service.NewsService;

/**
 * Created by YSAN on 2018/03/29
 */

public class EventBusActivity extends AppCompatActivity {

    private TextView mTvMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mTvMsg = findViewById(R.id.tv_message);
        Intent timeService = new Intent(this, EventbusService.class);
        startService(timeService);
        Intent newsService = new Intent(this, NewsService.class);
        startService(newsService);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Message msg) {
        String s = msg.getMsg();
        Log.i("ysan", "线程 >>>" + Thread.currentThread().getName() + "，收到消息" + s);
        mTvMsg.setText(s);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsEvent(News news) {
        String s = news.getInfo();
        Log.i("ysan", "线程 >>>" + Thread.currentThread().getName() + "，收到消息" + s);
        mTvMsg.setText(s);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onNewsevent(News news) {
        String s = news.getInfo();
        Log.i("ysan", "线程 >>>" + Thread.currentThread().getName() + "，收到消息" + s);
    }
}
