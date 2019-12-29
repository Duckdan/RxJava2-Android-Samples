package com.rxjava2.android.samples.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.ui.operators.AsyncSubjectExampleActivity;
import com.rxjava2.android.samples.ui.operators.BehaviorSubjectExampleActivity;
import com.rxjava2.android.samples.ui.operators.BufferExampleActivity;
import com.rxjava2.android.samples.ui.operators.CompletableObserverExampleActivity;
import com.rxjava2.android.samples.ui.operators.ConcatExampleActivity;
import com.rxjava2.android.samples.ui.operators.DebounceExampleActivity;
import com.rxjava2.android.samples.ui.operators.DeferExampleActivity;
import com.rxjava2.android.samples.ui.operators.DelayExampleActivity;
import com.rxjava2.android.samples.ui.operators.DisposableExampleActivity;
import com.rxjava2.android.samples.ui.operators.DistinctExampleActivity;
import com.rxjava2.android.samples.ui.operators.FilterExampleActivity;
import com.rxjava2.android.samples.ui.operators.FlowableExampleActivity;
import com.rxjava2.android.samples.ui.operators.IntervalExampleActivity;
import com.rxjava2.android.samples.ui.operators.LastOperatorExampleActivity;
import com.rxjava2.android.samples.ui.operators.MapExampleActivity;
import com.rxjava2.android.samples.ui.operators.MergeExampleActivity;
import com.rxjava2.android.samples.ui.operators.PublishSubjectExampleActivity;
import com.rxjava2.android.samples.ui.operators.ReduceExampleActivity;
import com.rxjava2.android.samples.ui.operators.ReplayExampleActivity;
import com.rxjava2.android.samples.ui.operators.ReplaySubjectExampleActivity;
import com.rxjava2.android.samples.ui.operators.ScanExampleActivity;
import com.rxjava2.android.samples.ui.operators.SimpleExampleActivity;
import com.rxjava2.android.samples.ui.operators.SingleAllOperatorActivity;
import com.rxjava2.android.samples.ui.operators.SingleObserverExampleActivity;
import com.rxjava2.android.samples.ui.operators.SkipExampleActivity;
import com.rxjava2.android.samples.ui.operators.SwitchMapExampleActivity;
import com.rxjava2.android.samples.ui.operators.TakeExampleActivity;
import com.rxjava2.android.samples.ui.operators.TakeUntilExampleActivity;
import com.rxjava2.android.samples.ui.operators.TakeWhileExampleActivity;
import com.rxjava2.android.samples.ui.operators.ThrottleFirstExampleActivity;
import com.rxjava2.android.samples.ui.operators.ThrottleLastExampleActivity;
import com.rxjava2.android.samples.ui.operators.TimerExampleActivity;
import com.rxjava2.android.samples.ui.operators.WindowExampleActivity;
import com.rxjava2.android.samples.ui.operators.ZipExampleActivity;


public class OperatorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);
    }

    public void startSimpleActivity(View view) {
        //最简单的用法
        startActivity(new Intent(OperatorsActivity.this, SimpleExampleActivity.class));
    }

    public void startMapActivity(View view) {
        //map操作符将获取到的结果转化为想要的结果之后交给，观察者处理
        startActivity(new Intent(OperatorsActivity.this, MapExampleActivity.class));
    }

    public void startZipActivity(View view) {
        //将两个被观察者转化为一个被观察者，将两个被观察者的结果合并处理
        startActivity(new Intent(OperatorsActivity.this, ZipExampleActivity.class));
    }

    public void startDisposableActivity(View view) {
        //使用该操作符可以防止Activity被销毁时，请求结果才返回的情形
        startActivity(new Intent(OperatorsActivity.this, DisposableExampleActivity.class));
    }

    public void startTakeActivity(View view) {
        //被观察者列表的第一个被观察者到指定数量的被观察者
        startActivity(new Intent(OperatorsActivity.this, TakeExampleActivity.class));
    }

    public void startTimerActivity(View view) {
        //延时发送操作符
        startActivity(new Intent(OperatorsActivity.this, TimerExampleActivity.class));
    }

    public void startIntervalActivity(View view) {
        //每个多长时间进行一次操作
        startActivity(new Intent(OperatorsActivity.this, IntervalExampleActivity.class));
    }

    public void startSingleObserverActivity(View view) {
        //Single及其操作符的使用
        startActivity(new Intent(OperatorsActivity.this, SingleAllOperatorActivity.class));
    }

    public void startCompletableObserverActivity(View view) {
        //指示完成的操作符
        startActivity(new Intent(OperatorsActivity.this, CompletableObserverExampleActivity.class));
    }

    public void startFlowableActivity(View view) {
        //Flowable中reduce做减法示例
        startActivity(new Intent(OperatorsActivity.this, FlowableExampleActivity.class));
    }

    public void startReduceActivity(View view) {
        //与上个示例类似
        startActivity(new Intent(OperatorsActivity.this, ReduceExampleActivity.class));
    }

    public void startBufferActivity(View view) {
        //重复以及跳跃式发送Observable
        startActivity(new Intent(OperatorsActivity.this, BufferExampleActivity.class));
    }

    public void startFilterActivity(View view) {
        //过滤filter
        startActivity(new Intent(OperatorsActivity.this, FilterExampleActivity.class));
    }

    public void startSkipActivity(View view) {
        //越过多少个Observable
        startActivity(new Intent(OperatorsActivity.this, SkipExampleActivity.class));
    }

    public void startScanActivity(View view) {
        //实现累加功能
        startActivity(new Intent(OperatorsActivity.this, ScanExampleActivity.class));
    }

    public void startReplayActivity(View view) {
        //重复订阅
        startActivity(new Intent(OperatorsActivity.this, ReplayExampleActivity.class));
    }

    public void startConcatActivity(View view) {
        //concat顺序执行,但其中一个observer调用OnError之后，后续observer仍然可以继续执行
        startActivity(new Intent(OperatorsActivity.this, ConcatExampleActivity.class));
    }

    public void startMergeActivity(View view) {
        //merge任意执行Observable
        startActivity(new Intent(OperatorsActivity.this, MergeExampleActivity.class));
    }

    public void startDeferActivity(View view) {
        //推迟创建
        startActivity(new Intent(OperatorsActivity.this, DeferExampleActivity.class));
    }

    public void startDistinctActivity(View view) {
        //去除重复元素
        startActivity(new Intent(OperatorsActivity.this, DistinctExampleActivity.class));
    }

    public void startLastOperatorActivity(View view) {
        //获取最后一个Observable来使用
        startActivity(new Intent(OperatorsActivity.this, LastOperatorExampleActivity.class));
    }

    public void startReplaySubjectActivity(View view) {
        //重复订阅
        startActivity(new Intent(OperatorsActivity.this, ReplaySubjectExampleActivity.class));
    }

    public void startPublishSubjectActivity(View view) {
        //单独订阅
        startActivity(new Intent(OperatorsActivity.this, PublishSubjectExampleActivity.class));
    }

    public void startBehaviorSubjectActivity(View view) {
        //类似交叉执行
        startActivity(new Intent(OperatorsActivity.this, BehaviorSubjectExampleActivity.class));
    }

    public void startAsyncSubjectActivity(View view) {
        //异步订阅
        startActivity(new Intent(OperatorsActivity.this, AsyncSubjectExampleActivity.class));
    }

    public void startThrottleFirstActivity(View view) {
        //给定一个时间范围，在这个时间范围内只取第一个数据源
        startActivity(new Intent(OperatorsActivity.this, ThrottleFirstExampleActivity.class));
    }

    public void startThrottleLastActivity(View view) {
        //给定一个时间范围，在这个时间范围内只取最后一个数据源
        startActivity(new Intent(OperatorsActivity.this, ThrottleLastExampleActivity.class));
    }

    public void startDebounceActivity(View view) {
        //防抖动
        startActivity(new Intent(OperatorsActivity.this, DebounceExampleActivity.class));
    }

    public void startWindowActivity(View view) {
        startActivity(new Intent(OperatorsActivity.this, WindowExampleActivity.class));
    }

    public void startDelayActivity(View view) {
        //延迟发送操作符
        startActivity(new Intent(OperatorsActivity.this, DelayExampleActivity.class));
    }

    public void startSwitchMapActivity(View view) {
        //只映射当前的被观察者
        startActivity(new Intent(OperatorsActivity.this, SwitchMapExampleActivity.class));
    }

    public void startTakeWhileActivity(View view) {
        //类似while循环，只有条件满足的时候才一直执行，之后就立马跳出
        startActivity(new Intent(OperatorsActivity.this, TakeWhileExampleActivity.class));
    }

    public void startTakeUntilActivity(View view) {
        //只要执行takeUntil里面的观察者之后，之前的观察者将全部失效
        startActivity(new Intent(OperatorsActivity.this, TakeUntilExampleActivity.class));
    }
}
