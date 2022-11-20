package com.example

import com.cdodi.Answer
import com.cdodi.HelloServiceGrpcKt
import com.cdodi.Person
import com.cdodi.Void
import io.ktor.server.application.*
import com.example.plugins.*
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureRouting()
}

fun channelForTarget(host: String, port: Int): ManagedChannel =
    ManagedChannelBuilder
        .forAddress("localhost", 50001)
        .usePlaintext()
        .build()

fun Person(init: Person.Builder.() -> Unit) =
    Person.newBuilder()
        .apply(init)
        .build()

fun Void(init: Void.Builder.() -> Unit) =
    Void.newBuilder()
        .apply(init)
        .build()
