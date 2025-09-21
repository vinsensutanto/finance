package com.example.finance.controller

import com.example.finance.entity.Category
import com.example.finance.entity.Transaction
import com.example.finance.entity.TransactionType
import com.example.finance.repository.TransactionRepository
import com.example.finance.service.BudgetService
import com.example.finance.service.TransactionService
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api")
class TransactionController (
    private val transactionService: TransactionService,
    private val budgetService: BudgetService
){
    @PostMapping("/transactions/expense")
    fun addExpense(
        @RequestParam name: String,
        @RequestParam category: Category,
        @RequestParam amount: Double
    ) = transactionService.addTransaction(name, category, amount, TransactionType.EXPENSE)

    @PostMapping("/transactions/income")
    fun addIncome(
        @RequestParam name: String,
        @RequestParam category: Category,
        @RequestParam amount: Double
    ) = transactionService.addTransaction(name, category, amount, TransactionType.INCOME)

    @PostMapping("/payment")
    fun pay(@RequestParam amount: Double) = transactionService.processPayment(amount)

    @PostMapping("/budgets")
    fun setBudget(
        @RequestParam category: Category,
        @RequestParam limit: Double,
        @RequestParam period: String
    ) = budgetService.setBudget(category, limit, period)

    @GetMapping("/statistics")
    fun getStatistics() = transactionService.getStatistics()

    @GetMapping("/notifications")
    fun getNotifications() = transactionService.getNotifications()
}