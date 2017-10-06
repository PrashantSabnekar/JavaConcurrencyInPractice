package com.prashant.java.intrinsiclocks;

/**
 * @author Prashant Sabnekar
 *
 */
public class Demo {

    private String id;

    public Demo(String id) {
        this.id = id;
    }

    public synchronized void objectIntrinsicLock() throws InterruptedException {
        Thread.sleep(100);
        System.out.println("objectIntrinsicLock " + id);
        Thread.sleep(100);
        classLock();
    }

    public void classLock() throws InterruptedException {
        synchronized(Demo.class) {
            Thread.sleep(100);
            System.out.println("classLock " + id);
        }
    }

    public void test() {
        try {
            objectIntrinsicLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        Demo d1 = new Demo("One");
        Demo d2 = new Demo("Two");
        Demo d3 = new Demo("Three");
        Demo d4 = new Demo("Four");

        MyThread t1 = new MyThread(d1);
        MyThread t2 = new MyThread(d2);
        MyThread t3 = new MyThread(d3);
        MyThread t4 = new MyThread(d4);

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        Thread thread3 = new Thread(t3);
        Thread thread4 = new Thread(t4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

class MyThread implements Runnable {
    private Demo demo;

    public MyThread(Demo demo) {
        this.demo = demo;
    }

    public void run() {
        demo.test();
    }
}