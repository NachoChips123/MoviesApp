package com.example.moviesapp.Domain;


import java.util.List;

public class Genres {

   private List<GenreItem> genres;

    public List<GenreItem> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreItem> genres) {
        this.genres = genres;
    }
}
