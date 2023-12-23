package com.example.parsedata.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.parsedata.data.database.MovieEntity
import com.example.parsedata.data.database.toMovie
import com.example.parsedata.domain.Movie
import com.example.parsedata.ui.theme.ParseDataTheme
import com.lubnamariyam.retrofitapiinjetpackcompose.view.MovieItem

class MainActivity : ComponentActivity() {

    private val movieViewModel by viewModels<MovieViewModel> { MovieViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParseDataTheme {
                Surface {
                    MovieList(movieList = movieViewModel.movieListResponse)
                    movieViewModel.getMovieList()
                }
            }
        }
    }
}

@Composable
fun MovieList(movieList: List<MovieEntity>) {
    LazyColumn {
        itemsIndexed(items = movieList) { index, item ->
            MovieItem(movie = item.toMovie())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ParseDataTheme {
        val movie = Movie(
            "Coco",
            "https://howtodoandroid.com/images/coco.jpg",
            "Coco is a 2017 American 3D computer-animated musical fantasy adventure film produced by Pixar",
            "Latest"
        )

        MovieItem(movie = movie )

    }
}