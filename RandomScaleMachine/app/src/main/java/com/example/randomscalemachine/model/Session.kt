package com.example.randomscalemachine.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Session(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("date")
    var date: String
)

data class Result(
    @SerializedName("string")
    var string: GuitarString,
    @SerializedName("time")
    val time: Int
)

enum class GuitarString {
    E, A, D, G, B
}

enum class Note {
    A, ASharp, B, C, CSharp, D, DSharp, E, F, FSharp, G, GSharp
}