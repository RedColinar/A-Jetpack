package com.opq.a.jetpack.pay

interface PaymentClassification

class HourlyClassification(val hourlyRate: Double) : PaymentClassification

class SalariedClassification(val salary: Double) : PaymentClassification

class CommissionedClassification(val commissionRate: Double, val salary: Double): PaymentClassification