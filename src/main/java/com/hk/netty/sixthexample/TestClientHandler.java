package com.hk.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomInt = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage;
        if(0 ==randomInt){
            myMessage = MyDataInfo.MyMessage
                    .newBuilder().setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                    .setPerson(MyDataInfo.Person.newBuilder()
                            .setName("张三")
                            .setAge(28)
                            .setAddress("关山大道")
                            .build()).build();
        }else if(1==randomInt){
            myMessage = MyDataInfo.MyMessage
                    .newBuilder().setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder()
                            .setName("一只狗")
                            .setAge(28)
                            .build()).build();
        }else {
            myMessage = MyDataInfo.MyMessage
                    .newBuilder().setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder()
                            .setCity("武漢")
                            .setName("一只猫")
                            .build()).build();
        }




        /*MyDataInfo.Person person= MyDataInfo.Person.newBuilder()
                .setName("张三").setAge(20).setAddress("北京").build();*/

        ctx.channel().writeAndFlush(myMessage);
    }
}
