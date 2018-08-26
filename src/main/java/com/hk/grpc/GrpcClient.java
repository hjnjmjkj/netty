package com.hk.grpc;

import com.hk.proto.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost",8899).usePlaintext(true).build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);
        MyResponse myResponse= blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());

        System.out.println(myResponse.getRealname());

        System.out.println("----------------------");

        Iterator<StudentResponse> iterator= blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
        while (iterator.hasNext()){
            StudentResponse studentResponse=iterator.next();
            System.out.println(studentResponse.getName()+", "+studentResponse.getAge()+", "+studentResponse.getCity());
        }

        System.out.println("----------------------");
    }
}
