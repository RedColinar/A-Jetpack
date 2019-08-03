package com.opq.a.jetpack.pay

/**
 * Created by panqiang at 2019-08-03
 */
class DeleteEmployeeTransaction(val empId: Int) : Transaction {
    override fun execute() {
        deleteEmployee(empId)
    }
}