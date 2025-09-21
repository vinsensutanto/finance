package com.example.finance.service

import org.springframework.stereotype.Service

interface PaymentService {
    fun pay(amount: Double): String
}