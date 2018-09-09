package com.hk.nio;

import io.netty.buffer.ByteBuf;

import java.io.RandomAccessFile;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class NioTest13 {
    public static void main(String[] args) throws Exception{
        String inputFile ="NioTest13_In.txt";
        String outputFile ="NioTest13_Out.txt";

        RandomAccessFile inputRandomAccessFile=new RandomAccessFile(inputFile,"r");
        RandomAccessFile outputRandomAccessFile=new RandomAccessFile(outputFile,"rw");

        long inputLenth=new File(inputFile).length();

        FileChannel inputFileChannel=inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel=outputRandomAccessFile.getChannel();

        MappedByteBuffer inputData=inputFileChannel.map(FileChannel.MapMode.READ_ONLY,0,inputLenth);

        Charset.availableCharsets().forEach((k,v)->{
            System.out.println(k+","+v);
        });


        Charset charset= Charset.forName("gbk");
        CharsetDecoder decoder=charset.newDecoder();
        CharsetEncoder encode=charset.newEncoder();

        CharBuffer charBuffer=decoder.decode(inputData);

        ByteBuffer outputData=encode.encode(charBuffer);

        outputFileChannel.write(outputData);
        inputRandomAccessFile.close();
        outputRandomAccessFile.close();

    }
}
