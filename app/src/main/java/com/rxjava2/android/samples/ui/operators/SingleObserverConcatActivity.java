package com.rxjava2.android.samples.ui.operators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.model.CheckMemory;
import com.rxjava2.android.samples.model.NetWork;

import io.reactivex.Single;

public class SingleObserverConcatActivity extends AppCompatActivity {

    private static final String TAG = SingleObserverExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

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
        Single.concat(Single.just(checkNetWork()), Single.just(checkMemory()));
    }

    private NetWork checkNetWork() {
        NetWork netWork = new NetWork("");
        return netWork;
    }

    private CheckMemory checkMemory() {
        CheckMemory checkMemory = new CheckMemory();
        return checkMemory;
    }


}
