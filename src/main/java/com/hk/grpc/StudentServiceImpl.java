package com.hk.grpc;

import com.hk.proto.*;
import com.hk.proto.StudentServiceGrpc.StudentServiceImplBase;
import io.grpc.stub.StreamObserver;

public class StudentServiceImpl extends StudentServiceImplBase {
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息："+request.getUsername());

        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();
    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到客户端信息："+request.getAge());

        responseObserver.onNext(StudentResponse.newBuilder().setName("张三").setAge(20).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("李四").setAge(20).setCity("上海").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("王五").setAge(20).setCity("广州").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("赵六").setAge(20).setCity("深圳").build());
        responseObserver.onCompleted();
    }
}
