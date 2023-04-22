package com.example.randomscalemachine.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Session(
    @PrimaryKey val id: String,
    val results: List<Result>,
    var date: Date
)

data class Result(
    var string: GuitarString,
    val time: Int
)

enum class GuitarString {
    E, A, D, G, B
}