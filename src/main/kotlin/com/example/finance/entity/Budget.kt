package com.example.finance.entity

import jakarta.persistence.*

@Entity
@Table(name = "budgets")
data class Budget(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Enumerated(EnumType.STRING)
    val category: Category = Category.NECESSITY,
    @Column(name = "budget_limit")
    val limit: Double = 0.0, // budget amount
    val period: String = "monthly" // monthly or weekly
)