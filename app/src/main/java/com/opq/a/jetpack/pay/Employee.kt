package com.opq.a.jetpack.pay

data class Employee(
    val id: Int,
    val name: String,
    val address: String
) {
    lateinit var paymentMethod: PaymentMethod
    lateinit var paymentClassification: PaymentClassification
    lateinit var paymentSchedule: PaymentSchedule
    lateinit var affiliation: Affiliation
}