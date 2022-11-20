package com.example


import com.cdodi.Answer
import com.cdodi.HelloServiceGrpcKt
import com.cdodi.Person
import com.cdodi.Void

class HelloService : HelloServiceGrpcKt.HelloServiceCoroutineImplBase() {
    override suspend fun sayHelloTo(request: Person): Answer =
        Answer.newBuilder()
            .setMsg("Hello from${request.name}")
            .build()

    override suspend fun answerToHello(request: Void): Answer =
        Answer.newBuilder()
            .setMsg("Working bitch")
            .build()
}