package com.opq.a.jetpack.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.opq.a.jetpack.R
import com.opq.a.jetpack.db.JetpackDatabase
import com.opq.a.jetpack.db.UserEntity
import kotlinx.android.synthetic.main.fragment_splash.*
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val TAG = "SplashFragment"

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

        bt_tip.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                if (!pop()) return@launch
                if (!pop()) return@launch
            }
        }
    }

    private suspend fun pop(): Boolean = suspendCoroutine {
        AlertDialog.Builder(requireContext())
            .setMessage("标题")
            .setNegativeButton("取消") { d, _ ->
                d.dismiss()
                it.resume(false)
            }
            .setPositiveButton("确定") { d, _ ->
                d.dismiss()
                it.resume(true)
            }
            .create().show()
    }

    private fun api(): String {
        Thread.sleep(1000)
        return "yes!"
    }

    private fun toast(text: String) {
        Toast.makeText(
            this@SplashFragment.requireContext(), text, Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = SplashFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}
