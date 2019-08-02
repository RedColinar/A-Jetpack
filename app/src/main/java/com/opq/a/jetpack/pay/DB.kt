package com.opq.a.jetpack.pay

import android.annotation.SuppressLint

@SuppressLint("UseSparseArrays")
private val map = HashMap<Int, Employee>()

fun getEmployee(empId: Int): Employee {
    return map[empId]!!
}

fun addEmployee(employee: Employee) {
    map[employee.id] = employee
}