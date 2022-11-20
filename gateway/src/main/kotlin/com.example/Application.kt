package com.example

import io.grpc.Server
import io.grpc.ServerBuilder
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    println("Something")
    val port = 50051
    val server = HelloWorldServer(port)
    server.start()
    server.blockUntilShutdown()
}

class HelloWorldServer(private val port: Int) {
    val server: Server = ServerBuilder
        .forPort(port)
        .addService(HelloService())
        .build()

    fun start() {
        server.start()
        println("Server started, listening on $port")
        Runtime.getRuntime().addShutdownHook(
            Thread {
                println("*** shutting down gRPC server since JVM is shutting down")
                this@HelloWorldServer.stop()
                println("*** server shut down")
            }
        )
    }

    private fun stop() {
        server.shutdown()
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }
}
