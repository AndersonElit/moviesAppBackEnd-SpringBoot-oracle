package com.moviesapplication.moviesApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.moviesapplication.moviesApp.model.MovieModel;

public interface ControllerInt {
	
	public void addMovie(@RequestBody MovieModel movie);
	
	public void deleteMovie(@RequestBody MovieModel movie);
	
	public void editMovie(@RequestBody MovieModel movie);
	
	public List<MovieModel> getallMovies();
	
	public List<MovieModel> getMoviesPerGenre(@PathVariable String genre);

}
