package com.example.finance.entity

import jakarta.persistence.*
import java.time.LocalDate

enum class TransactionType {
    INCOME,
    EXPENSE
}

enum class Category {
    FOODS_AND_DRINKS,
    NECESSITY,
    FUN,
    FUTURE
}
@Entity
@Table(name="transactions")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String = "",

    @Enumerated(EnumType.STRING)
    val category: Category = Category.NECESSITY,

    val amount: Double = 0.0,

    @Enumerated(EnumType.STRING)
    val type: TransactionType = TransactionType.EXPENSE,

    val date: LocalDate = LocalDate.now()
)