package com.hk.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        switch (msg.getDataType()){
            case PersonType:
                MyDataInfo.Person person=msg.getPerson();
                System.out.println(person.getName());
                System.out.println(person.getAge());
                System.out.println(person.getAddress());
                break;
            case DogType:
                MyDataInfo.Dog dog=msg.getDog();
                System.out.println(dog.getName());
                break;
            case CatType:
                MyDataInfo.Cat cat=msg.getCat();
                System.out.println(cat.getName());
                break;
        }

    }
}
