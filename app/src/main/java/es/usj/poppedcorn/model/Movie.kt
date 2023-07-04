package es.usj.poppedcorn.model

data class Movie(
    var movieId: String,
    var title: String,
    var description: String,
    var year: Int?,
    var rating: Double?
)
