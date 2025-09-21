package com.example.finance.repository

import com.example.finance.entity.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findAllByType(type: TransactionType): List<Transaction>
    fun findAllByCategory(category: Category): List<Transaction>
}

@Repository
interface BudgetRepository : JpaRepository<Budget, Long> {
    fun findByCategoryAndPeriod(category: Category, period: String): Budget?
}

@Repository
interface NotificationRepository : JpaRepository<Notification, Long>