package com.example.plugins

import com.cdodi.HelloServiceGrpcKt
import com.example.Person
import com.example.Void
import io.grpc.ManagedChannelBuilder
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    val channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build()
    val stub = HelloServiceGrpcKt.HelloServiceCoroutineStub(channel)

    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText("${stub.answerToHello(Void {})}")
        }
        post("/{name}") {
            val buddyNme = call.parameters["name"] ?: "UNKNOWN_BUDDY"
            val msg = stub.sayHelloTo(Person { name = buddyNme })
            println(msg)
            call.respondText(msg.msg)
        }
    }
    routing {
    }
}
