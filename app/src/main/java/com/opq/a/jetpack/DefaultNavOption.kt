package com.opq.a.jetpack

import androidx.navigation.navOptions

/**
 * Created by panqiang at 2019-06-29
 */
fun defaultNavOption() = navOptions {
    anim {
        enter = R.anim.nav_default_enter_anim
        exit = R.anim.nav_default_exit_anim
        popEnter = R.anim.nav_default_pop_enter_anim
        popExit = R.anim.nav_default_pop_exit_anim
    }
}