package com.example.nio.demonio;

import io.netty.buffer.ByteBuf;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DemoApplication {
    public static void main(String[] args){
        niofile2();
        //fileNio();
    }
    public static void niofile2(){
        RandomAccessFile accessFile = null;
        try{
            accessFile = new RandomAccessFile("src/text.txt","rw");
            FileChannel fileChannel = accessFile.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int bytesread = fileChannel.read(byteBuffer);
            while(bytesread != -1){
                byteBuffer.flip();
                while(byteBuffer.hasRemaining()){
                    System.out.print((char)byteBuffer.get());
                }
                byteBuffer.compact();
                bytesread = fileChannel.read(byteBuffer);
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(accessFile != null){
                    accessFile.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static void fileNio(){
        InputStream in = null;
        try{
            in = new BufferedInputStream(new FileInputStream("src/text.txt"));
            byte[] buffer = new byte[1024];
            int bytesread = in.read(buffer);
            while(bytesread != -1){
                for(int i = 0; i < bytesread;i++){
                    System.out.print((char)buffer[i]);
                }
                bytesread = in.read(buffer);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(in != null){
                    in.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
