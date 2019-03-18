package com.example.nio.demonio;

public class RotatePrint2 {

    static class SubTask implements Runnable{
        static int count = 0;
        int id;

        public SubTask(int _id){
            this.id = _id;
        }
        @Override
        public void run() {
            while(count <= 100){
                synchronized (SubTask.class){
                    while(count % 3 == id){
                        System.out.println("Current Thread :" + Thread.currentThread().getName()+ ",value=" + count++);
                        SubTask.class.notifyAll();
                    }
                    while (count >= 100){
                        SubTask.class.notifyAll();
                        return;
                    }
                    try{
                        SubTask.class.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public static void main(String[] args){
        for (int i = 0; i < 3; i++){
            new Thread(new SubTask(i),"打印线程" + i).start();
        }
    }
}
