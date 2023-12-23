package com.example.parsedata.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@Dao
@Singleton
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieEntity>
}