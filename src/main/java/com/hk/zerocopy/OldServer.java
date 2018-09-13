package com.hk.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class OldServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket=new ServerSocket(8899);

        while(true){
            Socket socket=serverSocket.accept();
            DataInputStream dateInputStream=new DataInputStream(socket.getInputStream());
            try {
                byte[] byteArray=new byte[4096];
                while (true){
                    int readCount= dateInputStream.read(byteArray,0,byteArray.length);
                    if (readCount<0){
                        break;
                    }
                }

            }catch (Exception ex){

            }
        }
    }
}
