package com.opq.a.jetpack.socket

import okhttp3.*
import okhttp3.internal.wait
import okio.ByteString
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * Created by panqiang at 2019-08-05
 */
class SocketTest {
    @Test
    fun testSocket() {
        val host = "103.40.194.106:10250"
        val id = "4396"
        val client = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        val tokenRequest = Request.Builder().url("http://$host/im/getToken/$id?secret=NdWXCOeEMEzSQuYV").build()
        val tokenResponse = client.newCall(tokenRequest).execute()
        tokenResponse.body.toString()

        val request = Request.Builder().url("").build()

        client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                println("open")
            }
            override fun onMessage(webSocket: WebSocket, text: String) {
                println("onMessage")
            }
            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                println("onMessage")
            }
            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                println("onClosing")
            }
            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                println("onClosed")
            }
            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                println("onFailure")
            }
        })
    }


}