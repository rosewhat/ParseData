package com.example.parsedata.presentation

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parsedata.data.ApiService
import com.example.parsedata.data.database.AppDatabase
import com.example.parsedata.data.database.MovieEntity
import kotlinx.coroutines.launch

class MovieViewModel(private val context: Context) : ViewModel() {

    var movieListResponse: List<MovieEntity> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    private val movieDao = AppDatabase.getInstance(context).movieDao()

    fun getMovieList() {
        viewModelScope.launch {
            try {
                movieListResponse = movieDao.getMovies()

                if (movieListResponse.isEmpty()) {
                    val apiService = ApiService.getInstance()
                    val moviesFromApi = apiService.getMovies()

                    val movieEntities = moviesFromApi.map {
                        MovieEntity(
                            name = it.name,
                            imageUrl = it.imageUrl,
                            desc = it.desc,
                            category = it.category
                        )
                    }
                    movieDao.insertMovies(movieEntities)

                    movieListResponse = movieEntities
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}