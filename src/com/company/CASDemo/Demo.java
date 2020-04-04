package com.company.CASDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo {
    volatile static int count=0;
    public static void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        //count++;
        int expectCount;
        while (!compareAndSwap((expectCount=getCount()),expectCount+1)){};
    }

    public static int getCount() {
        return count;
    }

    private synchronized static boolean compareAndSwap(int expC, int newC) {
        if (getCount()==expC){
            count=newC;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        long start_time=System.currentTimeMillis();
        int num_thread=100;
        CountDownLatch countDownLatch =new CountDownLatch(num_thread);
        for (int i = 0;i<num_thread;i++){
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    //行为:访问十次网站
                    try {
                         for (int j=0;j<10;j++) {
                        request();
                         }
                    }catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            countDownLatch.countDown();
                    }
                }
            });
            thread.start();
        }
        countDownLatch.await();
        long time =System.currentTimeMillis()-start_time;
        System.out.println("time: "+time+" count: "+getCount());
    }
}
