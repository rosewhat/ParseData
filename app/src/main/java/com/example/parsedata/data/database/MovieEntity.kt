package com.example.parsedata.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.parsedata.domain.Movie

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val imageUrl: String,
    val desc: String,
    val category: String
)

fun MovieEntity.toMovie(): Movie {
    return Movie(
        name = this.name,
        imageUrl = this.imageUrl,
        desc = this.desc,
        category = this.category
    )
}