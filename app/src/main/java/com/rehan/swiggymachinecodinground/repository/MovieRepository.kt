package com.rehan.swiggymachinecodinground.repository

import com.rehan.swiggymachinecodinground.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Collections.emptyList

// Repository to handle movie operations
class MovieRepository {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList()) // Holds the list of movies
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow() // Exposes movies as immutable flow

    private var movieId = 0 // Auto-incrementing ID for movies

    // Adds a new movie to the list
    fun addMovie(name: String) {
        val newMovie = Movie(movieId++, name)
        _movies.value = listOf(newMovie) + _movies.value // Adds movie to the top of the list
    }

    // Updates the movie name by ID
    fun updateMovie(id: Int, newName: String) {
        _movies.value = _movies.value.map { if (it.id == id) it.copy(name = newName) else it }
    }

    // Deletes a movie by ID
    fun deleteMovie(id: Int) {
        _movies.value = _movies.value.filter { it.id != id }
    }

    // Toggles watched status of a movie
    fun toggleWatched(id: Int) {
        _movies.value = _movies.value.map {
            if (it.id == id) it.copy(watched = !it.watched) else it
        }
    }
}
