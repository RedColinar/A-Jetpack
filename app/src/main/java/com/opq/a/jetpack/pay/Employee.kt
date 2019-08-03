package com.opq.a.jetpack.pay

import android.annotation.SuppressLint

data class Employee(
    val id: Int,
    val name: String,
    val address: String
) {
    lateinit var paymentMethod: PaymentMethod
    lateinit var paymentClassification: PaymentClassification
    lateinit var paymentSchedule: PaymentSchedule
    @SuppressLint("UseSparseArrays")
    val affiliations = HashMap<Int, Affiliation>()

    fun addAffiliation(affiliation: Affiliation) {
        affiliation as UnionAffiliation
        affiliations[affiliation.memberId] = affiliation
    }

    fun getAffiliation(memberId: Int): Affiliation {
        return affiliations[memberId]!!
    }

    fun removeAffiliation(memberId: Int) {
        affiliations.remove(memberId)
    }
}