package com.rxjava2.android.samples.ui.operators;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 * just操作符
 */
public class SimpleExampleActivity extends AppCompatActivity {

    private static final String TAG = SimpleExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;
    private EditText etOperate;

    private String operateCase = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        etOperate = (EditText) findViewById(R.id.et_operate);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputOperate = etOperate.getText().toString().trim();
                operateCase = (TextUtils.isEmpty(inputOperate) ? "just" : inputOperate).toLowerCase();
                doSomeWork();
            }
        });
    }

    /*
     * simple example to emit two value one by one
     */
    private void doSomeWork() {
        Observable<String> simpleObservable = null;
        switch (operateCase) {
            case "create":
                simpleObservable = getCreateObservable();
                break;
            case "defer":
                simpleObservable = getDeferObservable();
                break;
            case "empty":
                simpleObservable = getEmptyObservable();
                break;
            case "never":
                simpleObservable = getNeverObservable();
                break;
            case "fromarray":
                simpleObservable = getFromObservable();
                break;
            case "interval":
                simpleObservable = getIntervalObservable();
                break;
            case "range":
                simpleObservable = getRangeObservable();
                break;
            case "repeat":
                simpleObservable = getRepeatObservable();
                break;
            case "start":
                simpleObservable = getStartObservable();
                break;
            case "timer":
                simpleObservable = getTimerObservable();
                break;
            case "just":
            default:
                simpleObservable = getJustObservable();
                break;
        }
        simpleObservable
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    /**
     * create操作符
     *
     * @return
     */
    private Observable<String> getCreateObservable() {
        return Observable.create(emitter -> {
            emitter.onNext("create");
            emitter.onNext("哈哈");
            emitter.onComplete();
        });
    }

    /**
     * defer延迟发送
     *
     * @return
     */
    private Observable<String> getDeferObservable() {
        return Observable.defer(() -> {
            String currentName = Thread.currentThread().getName();
            Log.e(TAG, currentName);
            Thread.sleep(2000);
            return Observable.just("defer延迟创建");
        });
    }

    /**
     * empty空操作符
     * 发射一个空的Observable,
     * 该被观察者只会执行onComplete
     *
     * @return
     */
    private Observable<String> getEmptyObservable() {
        return Observable.empty();
    }

    /**
     * never操作符
     * 发射一个绝不会发送任何条目的通知
     * 什么方法都不执行
     *
     * @return
     */
    private Observable<String> getNeverObservable() {
        return Observable.never();
    }

    /**
     * fromArray操作符
     * 如果参数中存在null,那么将会调用观察者的onError方法
     *
     * @return
     */
    private Observable<String> getFromObservable() {
        return Observable.fromArray("曾毅", null, "平齐平坐", "权威");
    }


    /**
     * Interval操作符
     * initialDelay：最开始延迟多少进行发射数据
     * period：间隔多长时间之后再次发送数据
     * <p>
     * aLong初始值为0，每次发射就在前一个值上面增加1
     *
     * @return
     */
    private Observable<String> getIntervalObservable() {
        return getJustObservable().zipWith(Observable.interval(3, 3, TimeUnit.SECONDS), (s, aLong) -> "字符串：" + s + "long数据：" + aLong);
    }

    /**
     * Range操作符
     * 起始值为4
     * 发送数据的个数为3，不过发送数据的Observable超过3个也只发三个，小于3个那就只发送这么多的Observable
     */
    private Observable<String> getRangeObservable() {
        return getJustObservable().zipWith(Observable.range(4, 3), (s, aiInt) -> "字符串：" + s + "int数据：" + aiInt);
    }

    /**
     * Repeat操作符
     * 所有的Observable重复2次，重复完毕之后才会调用onComplete
     *
     * @return
     */
    private Observable<String> getRepeatObservable() {
        return getJustObservable().repeat(2);
    }

    /**
     * Start操作符
     * "老熊乡--红塔乡"插入到getJustObservable中返回
     *
     * @return
     */
    private Observable<String> getStartObservable() {
        return getJustObservable().startWith("老熊乡--红塔乡");
    }

    /**
     * 定时发送一个Observable,此处只发送了getJustObservable第一个Observable
     * @return
     */
    private Observable<String> getTimerObservable() {
        return getJustObservable().zipWith(Observable.timer(2000, TimeUnit.MILLISECONDS), (s, aLong) -> "字符串：" + s + "，long数据：" + aLong);
    }

    /**
     * just操作符,底层调用的就是Observable.fromArray方法，
     * 不过just中的元素如果存在null，那么将会被抛出空指针异常
     *
     * @return
     */
    private Observable<String> getJustObservable() {
        return Observable.just("Cricket", "Football", "赵", "钱", "孙", "李", "周", "吴", "郑", "王");
    }


    private Observer<String> getObserver() {
        return new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                textView.append(" onNext : value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError : " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onComplete");
            }
        };
    }


}