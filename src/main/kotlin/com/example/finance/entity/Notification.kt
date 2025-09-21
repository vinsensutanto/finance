package com.example.finance.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name="notifications")
data class Notification (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val message: String = "",
    val date: LocalDateTime = LocalDateTime.now()
)