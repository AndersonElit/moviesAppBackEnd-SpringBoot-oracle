package com.moviesapplication.moviesApp.service;

import java.util.List;

import com.moviesapplication.moviesApp.model.MovieModel;

public interface ServiceInt {
	
	public void addMovie(MovieModel movie);
	
	public void delMovie(MovieModel movie);
	
	public void edMovie(MovieModel movie);
	
	public List<MovieModel> listMovies();
	
	public List<MovieModel> listGenre(String genre);

}
