package com.turman.fb.rx;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by dqf on 2016/1/6.
 */
public class RxTests {

    public static void function1(){
        Observable<String> myObservable = Observable.just("Hello");

        Observer<String> myObserver = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        //Subscription mySubscription = myObservable.subscribe(myObserver);

        Action1<String> myAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s+" action1.");
            }
        };

        myObservable.subscribe(myAction);

        Observable<Integer> array = Observable.from(new Integer[]{1,2,3,4,5,6});
//        array.subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                System.out.println(integer);
//            }
//        });

        array.map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer * integer;
            }
        }).skip(2).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer % 2 != 0;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    public static void function2(){
        Observable.just("Hello,Rxjava!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " -DQF";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

    private static Observable<List<String>> query(String str){
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.add("G");
        return Observable.just(list);
    }

    private static String sets(String s) {
        return "DQF-"+s;
    }

    public static void function3(){
        query("Hello World")
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        for (String ls : strings) {
                            System.out.println(ls);
                        }
                    }
                });

        System.out.println("---------");

        query("Hello World")
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        Observable.from(strings)
                                .subscribe(new Action1<String>() {
                                    @Override
                                    public void call(String s) {
                                        System.out.println(s);
                                    }
                                });
                    }
                });

        System.out.println("---------");

        query("Hello World")
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> strings) {
                        return Observable.from(strings);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String o) {
                        System.out.println(o);
                    }
                });

        System.out.println("---------");

        query("Hello World")
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> strings) {
                        return Observable.from(strings);
                    }
                })
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.just(sets(s));
                    }
                })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !s.equals("DQF-C");
                    }
                })
                .take(3)
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("yes");
                    }
                })
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        //必定最后调用
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onNext(String s) {
                        //int i = 9/0;
                        System.out.println(s);
                    }
                });

    }

    public static void main(String[] args){
        function3();
    }
}
















