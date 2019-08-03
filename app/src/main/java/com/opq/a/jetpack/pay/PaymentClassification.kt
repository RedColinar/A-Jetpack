package com.opq.a.jetpack.pay

import java.util.*
import kotlin.collections.HashMap

interface PaymentClassification

class HourlyClassification(val hourlyRate: Double) : PaymentClassification {
    private val timeCards = HashMap<Date, TimeCard>()

    fun addTimeCard(timeCard: TimeCard) {
        timeCards[timeCard.date] = timeCard
    }

    fun getTimeCard(date: Date): TimeCard {
        return timeCards[date]!!
    }
}

class SalariedClassification(val salary: Double) : PaymentClassification

class CommissionedClassification(
    val commissionRate: Double,
    val salary: Double
) : PaymentClassification {
    private val salesReceipts = HashMap<Date, SalesReceipt>()

    fun addSalesReceipt(salesReceipt: SalesReceipt) {
        salesReceipts[salesReceipt.date] = salesReceipt
    }

    fun getSalesReceipt(date: Date) : SalesReceipt {
        return salesReceipts[date]!!
    }
}