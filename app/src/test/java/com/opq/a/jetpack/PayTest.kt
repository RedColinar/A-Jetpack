package com.opq.a.jetpack

import com.opq.a.jetpack.pay.*
import org.junit.Test

import org.junit.Assert.*

class PayTest {
    @Test
    fun testAddSalariedEmployee() {
        val empId = 1
        AddSalariedEmployee(empId, "Bob", "Home", 1000.00).execute()

        val employee = getEmployee(empId)
        assert("Bob" == employee.name)

        val paymentClassification = employee.paymentClassification
        assert(paymentClassification is SalariedClassification)
        paymentClassification as SalariedClassification
        assertEquals(1000.00, paymentClassification.salary, 0.00)

        val paymentSchedule = employee.paymentSchedule
        assert(paymentSchedule is MonthlySchedule)

        val paymentMethod = employee.paymentMethod
        assert(paymentMethod is HoldMethod)
    }

    @Test
    fun testAddHourlyEmployee() {

    }

    @Test
    fun testAddCommissionedEmployee() {

    }
}
