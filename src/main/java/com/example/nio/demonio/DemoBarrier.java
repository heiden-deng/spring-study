package com.example.nio.demonio;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DemoBarrier {
    final int N;
    final  float[][] data;
    final  CyclicBarrier cyclicBarrier;
    static volatile boolean done = false;

    static class Worker implements Runnable{
        int nRow;
        CyclicBarrier cyclicBarrier;
        public Worker(int n,CyclicBarrier cyclicBarrier){nRow = n;this.cyclicBarrier=cyclicBarrier;}
        @Override
        public void run() {
            while(!done){
                System.out.println("线程 " + Thread.currentThread().getName() + " 开始处理第 " + nRow +" 行数据");
                try {
                    cyclicBarrier.await();
                    System.out.println("线程 " + Thread.currentThread().getName() + " 处理完成 ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                done = true;
            }
        }
    }

    public DemoBarrier(float[][] matrix){
        data = matrix;
        N = matrix.length;
        cyclicBarrier = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有线程开始第一阶段完成，开始处理第二阶段");
            }
        });
    }
    public void start(){
        for (int i = 0; i < N; i++){
            new Thread(new Worker(i,cyclicBarrier)).start();
        }
    }
    public static void main(String[] args){
        DemoBarrier demoBarrier = new DemoBarrier(new float[][]{{1.0f,2.0f},{3.1f,5.0f},{5.0f,6.0f}});
        demoBarrier.start();
    }

}
