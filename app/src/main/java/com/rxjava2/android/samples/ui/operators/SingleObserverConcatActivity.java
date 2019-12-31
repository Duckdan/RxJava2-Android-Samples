package com.rxjava2.android.samples.ui.operators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.model.CheckMemory;
import com.rxjava2.android.samples.model.NetWork;
import com.rxjava2.android.samples.ui.compose.RxSchedulers;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SingleObserverConcatActivity extends AppCompatActivity {

    private static final String TAG = SingleObserverConcatActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    private RxSchedulers schedulers = new RxSchedulers();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signle_concat);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomeWork();
            }
        });
    }

    private void doSomeWork() {

        Single<String> map0 = Single.just(checkMemory())
                .map(memoryWork -> {
                    return "map0";
                });

        Single<String> map1 = Single
                .just(checkNetWork())
                .map(netWork -> {
                    String currentString = netWork.status;
                    int i = 8 / 0;
                    return currentString;
                });


        Single<String> map2 = Single.just(checkMemory())
                .map(memoryWork -> {
                    String memoryString = memoryWork.memoryStatus;
                    return memoryString;
                });


        Single.concat(
                map0,
                map1,
                map2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Log.e(TAG, result);
                }, throwable -> {
                    Log.e(TAG, throwable.getMessage());
                });

    }

    private NetWork checkNetWork() {
        NetWork netWork = new NetWork("11");
        return netWork;
    }

    private CheckMemory checkMemory() {
        CheckMemory checkMemory = new CheckMemory("22");
        return checkMemory;
    }


}
