package com.example.nio.demonio;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class DemoNetSelectServerApplication {
    private static final int BUF_SIZE=1024;
    private static final int TIMEOUT=3000;
    public static void main(String[] args){
        fileNio();
        //fileNio();
    }

    public static void handConnection(SelectionKey selectionKey) throws IOException{
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selectionKey.selector(),SelectionKey.OP_READ,ByteBuffer.allocateDirect(BUF_SIZE));
    }


    public static void handRead(SelectionKey selectionKey) throws IOException{
        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
        ByteBuffer byteBuffer = (ByteBuffer)selectionKey.attachment();
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

    public static void handWrite(SelectionKey selectionKey) throws IOException {
        ByteBuffer byteBuffer = (ByteBuffer)selectionKey.attachment();
        byteBuffer.flip();
        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
        while(byteBuffer.hasRemaining()){
            socketChannel.write(byteBuffer);
        }
        byteBuffer.compact();
    }

    public static void fileNio(){
        ServerSocketChannel serverSocketChannel = null;
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUF_SIZE);
        Selector selector = null;
        try{
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(8300));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server start at 8300");
            while(true){
                if(selector.select(TIMEOUT) == 0){
                    System.out.println("==");
                    continue;
                }
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while(iter.hasNext()){
                    SelectionKey selectionKey = iter.next();
                    if (selectionKey.isAcceptable()){
                        handConnection(selectionKey);
                    }else if(selectionKey.isReadable()){
                        handRead(selectionKey);
                    }else if (selectionKey.isWritable()){
                        handWrite(selectionKey);
                    }else if (selectionKey.isConnectable()){
                        System.out.println("isConnectable = true");
                    }
                    iter.remove();
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
