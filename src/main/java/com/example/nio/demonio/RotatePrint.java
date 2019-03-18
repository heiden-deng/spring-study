package com.example.nio.demonio;



public class RotatePrint {

    static class PrintThread implements Runnable {
        static int count = 0;
        @Override
        public void run() {
            while(count < 100){
                synchronized (PrintThread.class){
                    System.out.println("Current Thread id=" + Thread.currentThread().getName() + ",value=" + count++);
                    PrintThread.class.notify();
                    try {
                        PrintThread.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static void main(String[] args){
        new Thread(new PrintThread(),"奇数").start();
        new Thread(new PrintThread(),"偶数").start();
    }
}
