package com.moviesapplication.moviesApp.repository;

import java.util.List;

import com.moviesapplication.moviesApp.model.MovieModel;

public interface RepositoryInt {
	
	public void saveMovie(MovieModel movie);
	
	public void deleteMovie(String movName);
	
	public void editMovie(MovieModel movie);
	
	public List<MovieModel> allMovies();
	
	public List<MovieModel> moviesPerGenre(String genre);

}
