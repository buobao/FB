package com.turman.fb.example.rxjava;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.turman.fb.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by dqf on 2016/3/25.
 */
public class RxJaveActivity extends Activity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        image = (ImageView) findViewById(R.id.image);
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                final Drawable drawable = getResources().getDrawable(R.drawable.obama);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxJaveActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        image.setImageDrawable(drawable);
                    }
                });

        Observable.just(image)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ImageView>() {
                    @Override
                    public void call(ImageView imageView) {
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(RxJaveActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


        Observable.just(1)
                .lift(new Observable.Operator<String, Integer>() {
                    @Override
                    public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {
                        return new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                subscriber.onError(e);
                            }

                            @Override
                            public void onNext(Integer integer) {
                                subscriber.onNext("String" + integer);
                            }
                        };
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Toast.makeText(RxJaveActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
