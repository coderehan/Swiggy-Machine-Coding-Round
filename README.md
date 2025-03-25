<h2>Swiggy Machine Coding Round - Movie List App</h2>
<h3>Problem Statement:</h3>
<h3>Develop an Android application using Kotlin and Jetpack Compose that allows users to manage a list of movies they have watched or plan to watch.</h3>

<h3>Features:</h3> <br>
<b></b>Movie List Screen:</b> <br>
Displays a list of movies added by the user. <br>
Initially, the list is empty. <br>
<b>Add Movie:</b> <br>
A Floating Action Button (FAB) at the bottom-right opens a dialog. <br>
The dialog contains an EditText for entering the movie name and an "Add" button. <br>
Once a movie is added, it appears at the top of the list instantly. <br>
<b>Update Movie:</b> <br>
Each movie has an Edit button that opens a dialog. <br>
Users can modify the movie name and save changes. <br>
<b>Delete Movie:</b> <br>
Each movie has a Delete button to remove it from the list. <br>
<b>Mark as Watched:</b> <br>
Users can tap on a movie to toggle its "Watched" status. <br>
Watched movies will have a strikethrough effect. <br>

<h3>Tech Stack & Architecture:</h3> <br>
<b>Language:</b> Kotlin <br>
<b>UI:</b> Jetpack Compose <br>
<b>Architecture:</b> MVVM (Model-View-ViewModel) <br>
<b>State Management:</b> StateFlow <br>

This is a non-API-based CRUD (Create, Read, Update, Delete) application that only manages local movie data within the app.
