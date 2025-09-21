package com.example.finance.service
import org.springframework.stereotype.Service

@Service
class PaymentProcessor(
    private val paymentServices: List<PaymentService>
) {
    fun process(amount: Double, provider: String): String {
        val service = paymentServices.find { it.getProvider().equals(provider, ignoreCase = true) }
            ?: throw IllegalArgumentException("Unsupported payment provider: $provider")

        return service.pay(amount)
    }
}