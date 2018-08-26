package com.hk.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import static com.hk.protobuf.DataInfo.*;

public class ProtobufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Student student= Student.newBuilder().setName("黄凯").setAge(28).setAddress("关山大道").build();
        byte[] student2ByteArray =  student.toByteArray();
        DataInfo.Student student2 =DataInfo.Student.parseFrom(student2ByteArray);
        System.out.println( student2.toString());
        System.out.println( student2.getName());
        System.out.println( student2.getAge());
        System.out.println( student2.getAddress());
    }
}
