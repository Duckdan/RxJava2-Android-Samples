package com.rxjava2.android.samples.ui.operators;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.ui.OperatorsActivity;

/**
 * Single操作集合
 */
public class SingleAllOperatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_all_operator);
    }

    public void startSingleJustActivity(View view) {
        startActivity(new Intent(SingleAllOperatorActivity.this, SingleObserverExampleActivity.class));
    }

    public void startSingleConcatActivity(View view) {
        startActivity(new Intent(SingleAllOperatorActivity.this, SingleObserverConcatActivity.class));
    }
}
