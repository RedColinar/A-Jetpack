package com.opq.a.jetpack.ui

import android.content.Context
import android.net.LocalServerSocket
import android.net.LocalSocket
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.facebook.stetho.common.LogUtil
import com.opq.a.jetpack.R
import com.opq.a.jetpack.db.JetpackDatabase
import com.opq.a.jetpack.db.UserEntity
import kotlinx.android.synthetic.main.fragment_splash.*

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.InterruptedIOException
import java.net.SocketException


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SplashFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navController = findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_to_login.setOnClickListener {
            // action 或者 destination id
            // navController.navigate(R.id.loginFragment)
            navController.navigate(
                R.id.action_splashFragment_to_loginFragment, null,
                defaultNavOption()
            )
        }
        bt_to_advertisement.setOnClickListener {
            navController.navigate(
                R.id.action_splashFragment_to_advertisementFragment, null,
                defaultNavOption()
            )
        }

        JetpackDatabase.instance().userEntityDao().insertAll(UserEntity("1", "opq"))

        listenDevTool()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SplashFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun listenDevTool() {
        // chrome://inspect/#devices
        val thread = Thread {
            val address = "opq_devtools_remote"
            val serverSocket = LocalServerSocket(address)
            LogUtil.i("Listening on @$address")

            while (!Thread.interrupted()) {
                try {
                    val socket: LocalSocket = serverSocket.accept()
                    // Start worker thread
//                    val t: Thread = LocalSocketServer.WorkerThread(socket, mSocketHandler)
//                    t.name = LocalSocketServer.WORKER_THREAD_NAME_PREFIX +
//                            "-" + mFriendlyName +
//                            "-" + mThreadId.incrementAndGet()
//                    t.isDaemon = true
//                    t.start()
                    val s = BufferedReader(InputStreamReader(socket.inputStream, "UTF-8"))
                    s.forEachLine {
                        Log.d(address, it)
                    }
                } catch (se: SocketException) { // ignore exception if interrupting the thread
                    if (Thread.interrupted()) {
                        break
                    }
                    Log.d(address, "I/O error")
                } catch (ex: InterruptedIOException) {
                    break
                } catch (e: IOException) {
                    LogUtil.w(
                        address,
                        "I/O error initialising connection thread"
                    )
                    break
                }
            }

            Log.d(address, "Server shutdown on @$address")
        }
        thread.isDaemon = true
        thread.start()
    }
}
