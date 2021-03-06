package com.hk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

public class NioServer {
    private static Map<String,SocketChannel> clientMap=new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket=serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector= Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            try {
                selector.select();
                Set<SelectionKey> selectionKeys =selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    SocketChannel client=null;
                    try{
                        if(selectionKey.isAcceptable()){
                            ServerSocketChannel server= (ServerSocketChannel) selectionKey.channel();
                            client=server.accept();
                            client.configureBlocking(false);
                            client.register(selector,SelectionKey.OP_READ);

                            String key="【"+ UUID.randomUUID().toString()+"】";

                            clientMap.put(key,client);
                        }else if(selectionKey.isReadable()){
                            client=(SocketChannel)selectionKey.channel();
                            ByteBuffer readBuffer =ByteBuffer.allocate(1024);

                            int count=client.read(readBuffer);

                            if(count>0){
                                readBuffer.flip();
                                Charset charset = Charset.forName("GBK");
                                String receiveMessage =String.valueOf(charset.decode(readBuffer).array());

                                System.out.println(client+":"+receiveMessage);
                                String senderKey=null;
                                for (Map.Entry<String,SocketChannel> entry:clientMap.entrySet()){
                                    if(client == entry.getValue()){
                                        senderKey=entry.getKey();
                                        break;
                                    }
                                }
                                for (Map.Entry<String,SocketChannel> entry:clientMap.entrySet()){
                                    SocketChannel value=entry.getValue();
                                    ByteBuffer writeBuffer =ByteBuffer.allocate(1024);
                                    writeBuffer.put((senderKey+":"+receiveMessage).getBytes("GBK"));
                                    writeBuffer.flip();
                                    value.write(writeBuffer);
                                }

                            }else{
                                close(client);
                            }
                        }
                    }catch (Exception ex){
                        close(client);
                        ex.printStackTrace();
                    }
                });
                selectionKeys.clear();


            }catch (Exception ex){
                ex.printStackTrace();
            }
        }


    }

    private static void close(SocketChannel client) {
        Iterator<String> iter = clientMap.keySet().iterator();

        while(iter.hasNext()) {

            String key = iter.next();

            if(clientMap.get(key)==client){
                iter.remove();
            }

        }
        System.out.println("client是否连接："+client.isConnected());
        System.out.println("client是否注册："+client.isRegistered());
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
