
plugins {
    id("my-ktor-application")
}

dependencies {
    implementation(project(":api"))
    runtimeOnly("io.grpc:grpc-netty:1.47.0")
}
