package com.opq.a.jetpack.coroutine

import org.junit.Test
import kotlin.coroutines.*

/**
 * Created by panqiang at 2020/8/18
 *
 * https://aisia.moe/2018/02/08/kotlin-coroutine-kepa/
 */
class CoroutineTest {

    // R.() -> T, 表示第一个参数是 类型为 R 的函数类型，返回值为 T
    fun <R, T> launchCoroutine(receiver: R, block: suspend R.() -> T) {
        block.startCoroutine(receiver, object : Continuation<T> {
            override val context: CoroutineContext = EmptyCoroutineContext
            override fun resumeWith(result: Result<T>): Unit = println(result.getOrThrow())
        })
    }

    @Test
    fun testCoroutine() {
        // 创建协程
        val continuation = suspend {

            print("5")
            5
        }.createCoroutine(object : Continuation<Int> {
            override val context: CoroutineContext = EmptyCoroutineContext
            override fun resumeWith(result: Result<Int>) {
                println("End $result")
            }
        })

        continuation.resume(Unit)

        suspend {
            print("5")
            5
        }.startCoroutine(object : Continuation<Int> {
            override val context: CoroutineContext = LogInterceptor()
            override fun resumeWith(result: Result<Int>) {
                println("Coroutine End $result")
            }
        })
    }
}

class LogInterceptor : ContinuationInterceptor {
    override val key = ContinuationInterceptor

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> =
        LogContinuation(continuation)
}

class LogContinuation<T>(private val continuation: Continuation<T>) :
    Continuation<T> by continuation {

    override fun resumeWith(result: Result<T>) {
        println("before ")
        continuation.resumeWith(result)
        println("after ")
    }
}