package com.rehan.swiggymachinecodinground.ui.screens

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rehan.swiggymachinecodinground.model.Movie
import com.rehan.swiggymachinecodinground.ui.theme.SwiggyMachineCodingRoundTheme
import com.rehan.swiggymachinecodinground.viewmodel.MovieViewModel

class MainActivity : ComponentActivity() {

    private val movieViewModel: MovieViewModel by viewModels() // ViewModel instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieListScreen(movieViewModel) }
            }
        }
    }
}

@Composable
fun MovieListScreen(viewModel: MovieViewModel) {
    val movies by viewModel.movies.collectAsState() // Observes movie list from ViewModel

    var showDialog by remember { mutableStateOf(false) } // Controls dialog visibility

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+") // Floating action button to add movies
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(text = "Movie List", style = MaterialTheme.typography.h5)

            LazyColumn {
                items(movies) { movie ->
                    MovieItem(movie, viewModel)
                }
            }
        }
    }

    // Show the add movie dialog when button is clicked
    if (showDialog) {
        AddMovieDialog(
            onDismiss = { showDialog = false },
            onAdd = { name ->
                viewModel.addMovie(name)
                showDialog = false
            }
        )
    }
}

@Composable
fun MovieItem(movie: Movie, viewModel: MovieViewModel) {
    var showEditDialog by remember { mutableStateOf(false) } // Controls edit dialog visibility

    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .clickable { viewModel.toggleWatched(movie.id) }, // Toggle watched on click
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = movie.name,
            modifier = Modifier.weight(1f).padding(8.dp),
            textDecoration = if (movie.watched) TextDecoration.LineThrough else null // Strikethrough if watched
        )

        // Edit Button
        IconButton(onClick = { showEditDialog = true }) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        }

        // Delete Button
        IconButton(onClick = { viewModel.deleteMovie(movie.id) }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }

    // Show the edit movie dialog
    if (showEditDialog) {
        EditMovieDialog(
            initialName = movie.name,
            onDismiss = { showEditDialog = false },
            onUpdate = { newName ->
                viewModel.updateMovie(movie.id, newName)
                showEditDialog = false
            }
        )
    }
}

@Composable
fun AddMovieDialog(onDismiss: () -> Unit, onAdd: (String) -> Unit) {
    var movieName by remember { mutableStateOf("") } // Stores user input

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Movie") },
        text = {
            Column {
                TextField(value = movieName, onValueChange = { movieName = it })
            }
        },
        confirmButton = {
            Button(onClick = { if (movieName.isNotEmpty()) onAdd(movieName) }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun EditMovieDialog(initialName: String, onDismiss: () -> Unit, onUpdate: (String) -> Unit) {
    var movieName by remember { mutableStateOf(initialName) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Movie") },
        text = {
            Column {
                TextField(value = movieName, onValueChange = { movieName = it })
            }
        },
        confirmButton = {
            Button(onClick = { if (movieName.isNotEmpty()) onUpdate(movieName) }) {
                Text("Update")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieListScreen(MovieViewModel())
}
