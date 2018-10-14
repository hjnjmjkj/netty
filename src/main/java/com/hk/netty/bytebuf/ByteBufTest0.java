package com.hk.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufTest0 {
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0; i < 15; i++) {
            buffer.writeByte(i);
        }
        for (int i = 0; i < buffer.writerIndex(); i++) {
                System.out.println(buffer.getByte(i));
        }
        System.out.println(buffer.capacity());
    }
}
