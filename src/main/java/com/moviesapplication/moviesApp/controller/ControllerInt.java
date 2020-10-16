package com.moviesapplication.moviesApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.moviesapplication.moviesApp.exception.blankException;
import com.moviesapplication.moviesApp.model.MovieModel;

public interface ControllerInt {
	
	public void addMovie(@RequestBody MovieModel movie) throws blankException;
	
	public void deleteMovie(@PathVariable String movName);
	
	public void editMovie(@RequestBody MovieModel movie) throws blankException;
	
	public List<MovieModel> getallMovies();
	
	public List<MovieModel> getMoviesPerGenre(@PathVariable String genre);

}
