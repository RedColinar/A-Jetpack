package com.opq.a.jetpack.socket

import java.io.InputStream
import java.net.ServerSocket
import java.net.Socket

val MAX_BUFFER_SIZE = 1024

class BaseSocketServer() {

    lateinit var server: ServerSocket
    lateinit var socket: Socket
    var port: Int = 0
    lateinit var inputStream: InputStream

    constructor(port: Int): this() {
        this.port = port
    }

    fun runServerSingle() {
        server = ServerSocket(port)
        socket = server.accept()

        inputStream = socket.getInputStream()
        val ba = ByteArray(MAX_BUFFER_SIZE)

        val stringBuilder = StringBuilder()

        var length: Int

        while (inputStream.read(ba).also { length = it } != -1) {
            stringBuilder.append(String(ba, 0, length))
        }

        println(stringBuilder)

        inputStream.close()
        socket.close()
        server.close()
    }
}

fun main() {
    BaseSocketServer(9799).runServerSingle()
}