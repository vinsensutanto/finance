package com.example.finance.service

import com.example.finance.entity.Category
import com.example.finance.entity.Notification
import com.example.finance.entity.Transaction
import com.example.finance.entity.TransactionType
import com.example.finance.repository.BudgetRepository
import com.example.finance.repository.NotificationRepository
import com.example.finance.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val budgetRepository: BudgetRepository,
    private val notificationRepository: NotificationRepository,
    @Qualifier("bcaPaymentService") private val paymentService: PaymentService
) {
    fun addTransaction(
        name: String,
        category: Category,
        amount: Double,
        type: TransactionType
    ): Transaction {
        val transaction = Transaction(
            name = name,
            category = category,
            amount = amount,
            type = type
        )
        transactionRepository.save(transaction)
        checkBudget(transaction)
        return transaction
    }
    fun processPayment(amount: Double): String {
        return paymentService.pay(amount)
    }

    private fun checkBudget(transaction: Transaction) {
        val period = "monthly" // or dynamic
        val budget = budgetRepository.findByCategoryAndPeriod(transaction.category, period)
        if (budget != null) {
            val totalSpent = transactionRepository
                .findAllByCategory(transaction.category)
                .filter { it.type == TransactionType.EXPENSE }
                .sumOf { it.amount }
            if (totalSpent > budget.limit) {
                val notification = Notification(
                    message = "You exceeded your $period budget for ${transaction.category}!"
                )
                notificationRepository.save(notification)
            }
        }
    }

    fun getStatistics(): Map<String, Any> {
        val incomes = transactionRepository.findAllByType(TransactionType.INCOME).sumOf { it.amount }
        val expenses = transactionRepository.findAllByType(TransactionType.EXPENSE).sumOf { it.amount }
        val remainingBudget = budgetRepository.findAll().associate { b ->
            val spent = transactionRepository.findAllByCategory(b.category)
                .filter { it.type == TransactionType.EXPENSE }
                .sumOf { it.amount }
            b.category to (b.limit - spent)
        }
        return mapOf(
            "totalIncome" to incomes,
            "totalExpense" to expenses,
            "remainingBudget" to remainingBudget
        )
    }

    fun getNotifications(): List<Notification> {
        return notificationRepository.findAll()
    }
}