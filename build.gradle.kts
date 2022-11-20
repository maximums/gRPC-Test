
group = "com.example"
version = "0.0.1"

plugins {
    id("my-ktor-application")
}

dependencies {
    implementation(project(":api"))
    runtimeOnly("io.grpc:grpc-netty:1.47.0")
}