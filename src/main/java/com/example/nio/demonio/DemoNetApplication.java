package com.example.nio.demonio;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class DemoNetApplication {
    public static void main(String[] args){
        fileNio();
        //fileNio();
    }

    public static void fileNio(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        try{
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1",8300));
            if(socketChannel.finishConnect()){
                int i = 0;
                while(true){
                    TimeUnit.SECONDS.sleep(1);
                    String info = "I'm "+i+++"-th information from client";
                    byteBuffer.clear();
                    byteBuffer.put(info.getBytes());
                    byteBuffer.flip();
                    while(byteBuffer.hasRemaining()){
                        System.out.println(byteBuffer);
                        socketChannel.write(byteBuffer);
                    }
                }
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }finally {
            try{
                if(socketChannel != null){
                    socketChannel.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
