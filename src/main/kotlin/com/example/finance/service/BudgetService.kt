package com.example.finance.service

import com.example.finance.entity.Budget
import com.example.finance.entity.Category
import com.example.finance.repository.BudgetRepository
import org.springframework.stereotype.Service

@Service
class BudgetService(private val budgetRepository: BudgetRepository) {

    fun setBudget(category: Category, limit: Double, period: String): Budget {
        val existingBudget = budgetRepository.findByCategoryAndPeriod(category, period)
        return if (existingBudget != null) {
            val updated = existingBudget.copy(limit = limit)
            budgetRepository.save(updated)
        } else {
            val budget = Budget(category = category, limit = limit, period = period)
            budgetRepository.save(budget)
        }
    }

    fun getAllBudgets(): List<Budget> = budgetRepository.findAll()
}