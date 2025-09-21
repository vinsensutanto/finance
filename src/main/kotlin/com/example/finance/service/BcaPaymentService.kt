package com.example.finance.service

import org.springframework.stereotype.Service

@Service
class BcaPaymentService : PaymentService {
    override fun pay(amount: Double): String {
        return "Paid $amount using BCA"
    }
    override fun getProvider(): String = "bca"
}