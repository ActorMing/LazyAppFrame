package com.lazy.lazyappframe.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lazy.lazydevelopeframe.base.api.BaseSubscriber;
import com.lazy.lazydevelopeframe.base.exception.LazyThrowable;
import com.lazy.lazydevelopeframe.base.rxjava.RxSchedulerManager;
import com.tapadoo.alerter.Alerter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private Unbinder unbinder;
    @BindView(R.id.tv_main)
    TextView tvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        initialize();
    }

    private void initialize() {

        Flowable<String> compose = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {

            }
        }, BackpressureStrategy.DROP)
                .compose(RxSchedulerManager.io_main())
                .compose(RxSchedulerManager.handleErrTransformer());

        Disposable disposable = compose.subscribeWith(new BaseSubscriber<String>() {
            @Override
            public void onError(LazyThrowable throwable) {

            }

            @Override
            public void onNext(int code, String msg, String response) {

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            try {
                unbinder.unbind();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.tv_main)
    public void onViewClicked() {
        Alerter.create(MainActivity.this)
                .setTitle("Alert Title")
                .setText("Alert text...")
                .setDuration(3000)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Alerter.isShowing()) {
                            Alerter.hide();
                        }
                    }
                })
                .show();
    }
}
