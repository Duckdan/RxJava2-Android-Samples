package com.rxjava2.android.samples.ui.operators;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.model.NetWork;
import com.rxjava2.android.samples.utils.AppConstant;

import android.support.v7.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class ConcatExampleActivity extends AppCompatActivity {

    private static final String TAG = ConcatExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = findViewById(R.id.btn);
        textView = findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomeWork();
            }
        });
    }

    /*
     * Using concat operator to combine Observable : concat maintain
     * the order of Observable.
     * It will emit all the 7 values in order
     * here - first "A1", "A2", "A3", "A4" and then "B1", "B2", "B3"
     * first all from the first Observable and then
     * all from the second Observable all in order
     */
    private void doSomeWork() {
        final String[] aStrings = {"A1", "A2", "A3", "A4"};
        final NetWork[] bStrings = {new NetWork("status1"), new NetWork("status2"), new NetWork("status3")};

        final Observable<String> aObservable = Observable.fromArray(aStrings);
        final Observable<NetWork> bObservable = Observable.fromArray(bStrings);

        Observable.concat(aObservable, bObservable)
                .subscribe(getObserver());
    }


    private Observer<Object> getObserver() {
        return new Observer<Object>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Object value) {
                String valueStr = "";
                if (value instanceof String)
                    valueStr = (String) value;
                else
                    valueStr = ((NetWork) value).status;
                textView.append(" onNext : value : " + valueStr);
                if ("A3".equals(valueStr)) {
                    onError(new Throwable("Throwable"));
                }
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext : value : " + valueStr);
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