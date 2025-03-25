package com.rehan.swiggymachinecodinground.model

// Data class representing a Movie
data class Movie(
    val id: Int,       // Unique ID for each movie
    var name: String,  // Movie name entered by the user
    var watched: Boolean = false // Flag to track if the movie is watched
)
