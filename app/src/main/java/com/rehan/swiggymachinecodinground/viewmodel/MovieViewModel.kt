package com.rehan.swiggymachinecodinground.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rehan.swiggymachinecodinground.repository.MovieRepository
import com.rehan.swiggymachinecodinground.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel to interact with the Repository and manage UI state
@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository // Injecting repository using Hilt
) : ViewModel() {

    val movies: StateFlow<List<Movie>> = repository.movies // Exposes movie list to UI

    // Adds a movie inside a coroutine
    fun addMovie(name: String) {
        viewModelScope.launch {
            repository.addMovie(name)
        }
    }

    // Updates movie name inside a coroutine
    fun updateMovie(id: Int, newName: String) {
        viewModelScope.launch {
            repository.updateMovie(id, newName)
        }
    }

    // Deletes a movie inside a coroutine
    fun deleteMovie(id: Int) {
        viewModelScope.launch {
            repository.deleteMovie(id)
        }
    }

    // Toggles watched status inside a coroutine
    fun toggleWatched(id: Int) {
        viewModelScope.launch {
            repository.toggleWatched(id)
        }
    }
}
