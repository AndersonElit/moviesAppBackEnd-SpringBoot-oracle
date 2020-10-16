package com.moviesapplication.moviesApp.service;

import java.util.List;

import com.moviesapplication.moviesApp.exception.blankException;
import com.moviesapplication.moviesApp.model.MovieModel;

public interface ServiceInt {
	
	public void addMovie(MovieModel movie) throws blankException;
	
	public void delMovie(String movName);
	
	public void edMovie(int id, String name, String description, String actor, float income) throws blankException;
	
	public List<MovieModel> listMovies();
	
	public List<MovieModel> listGenre(String genre);

}
