package com.example.nio.demonio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class DemoNetServerApplication {
    public static void main(String[] args){
        fileNio();
        //fileNio();
    }

    public static void fileNio(){
        ServerSocketChannel serverSocketChannel = null;
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try{
            serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(8300));
            System.out.println("Server start at 8300");
            while(true){
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null){
                    SocketAddress socketAddress = socketChannel.getRemoteAddress();
                    System.out.println("Handle client at " + socketAddress.toString());
                    byteBuffer.clear();
                    int readbytes = socketChannel.read(byteBuffer);
                    while(readbytes > 0){
                        byteBuffer.flip();
                        while(byteBuffer.hasRemaining()){
                            System.out.print((char)byteBuffer.get());
                        }
                        System.out.println();
                        byteBuffer.compact();
                        readbytes = socketChannel.read(byteBuffer);
                    }
                    if(readbytes == -1){
                        socketChannel.close();
                    }
                }
            }
        }catch (IOException  e){
            e.printStackTrace();
        }finally {
            try{
                if(serverSocketChannel != null){
                    serverSocketChannel.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
