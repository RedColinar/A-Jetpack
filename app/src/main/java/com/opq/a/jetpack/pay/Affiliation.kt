package com.opq.a.jetpack.pay

import java.util.*
import kotlin.collections.HashMap

interface Affiliation

class UnionAffiliation(val memberId: Int, val dues: Double) : Affiliation {

    private val serviceCharges = HashMap<Date, ServiceCharge>()

    fun addServiceCharge(serviceCharge: ServiceCharge) {
        serviceCharges[serviceCharge.date] = serviceCharge
    }

    fun getServiceCharge(date: Date): ServiceCharge {
        return serviceCharges[date]!!
    }
}