package com.example.finance.service

import org.springframework.stereotype.Service

@Service
class OvoPaymentService : PaymentService {
    override fun pay(amount: Double): String {
        return "Paid $amount using Ovo"
    }
}