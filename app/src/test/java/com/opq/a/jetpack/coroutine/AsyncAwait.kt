package com.opq.a.jetpack.coroutine


import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.*

/**
 * Created by panqiang at 2020/8/20
 */
interface AsyncScope

fun async(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend AsyncScope.() -> Unit
) {
    val completion = AsyncCoroutine(context)
    block.startCoroutine(completion, completion)
}

class AsyncCoroutine(override val context: CoroutineContext = EmptyCoroutineContext) :
    Continuation<Unit>, AsyncScope {
    override fun resumeWith(result: Result<Unit>) {
        result.getOrThrow()
    }
}

suspend fun AsyncScope.await(block: () -> Call) = suspendCoroutine<Any?> { continuation ->
    val call = block()
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            continuation.resumeWithException(e)
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                response.body ?: continuation.resumeWithException(NullPointerException())
                continuation.resume(response.body)
            } else {
                continuation.resumeWithException(Exception())
            }
        }
    })
}

fun main() {

}