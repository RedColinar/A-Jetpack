package com.opq.a.jetpack.socket

import java.io.OutputStream
import java.net.Socket

class BaseSocketClient() {
    lateinit var serverHost: String
    var serverPort: Int = 0
    lateinit var socket: Socket
    lateinit var outputStream: OutputStream

    constructor(serverHost: String, serverPort: Int): this() {
        this.serverHost = serverHost
        this.serverPort = serverPort
    }

    fun connectServer() {
        socket = Socket(serverHost, serverPort)
        outputStream = socket.getOutputStream()
    }

    fun sendSingle(message: String) {
        outputStream.write(message.toByteArray(Charsets.UTF_8))

        outputStream.close()
        socket.close()
    }
}

fun main() {
    val client = BaseSocketClient("127.0.0.1", 9799)
    client.connectServer()
    client.sendSingle("hello")
}