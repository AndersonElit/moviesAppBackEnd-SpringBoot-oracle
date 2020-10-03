package com.moviesapplication.moviesApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.moviesapplication.moviesApp.exception.blankException;
import com.moviesapplication.moviesApp.model.MovieModel;
import com.moviesapplication.moviesApp.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MovieController implements ControllerInt {
	
	@Autowired
	private MovieService service;
	
	@RequestMapping(value="/addMovie", method = RequestMethod.POST)
	public void addMovie(@RequestBody MovieModel movie) throws blankException {
		service.addMovie(movie);
	}
	
	@RequestMapping(value="/deleteMovie", method = RequestMethod.POST)
	public void deleteMovie(@RequestBody MovieModel movie) throws blankException {
		service.delMovie(movie);
	}
	
	@RequestMapping(value="/editMovie", method = RequestMethod.POST)
	public void editMovie(@RequestBody MovieModel movie) throws blankException {
		service.edMovie(movie);
	}
	
	@RequestMapping(value="/allMovies", method = RequestMethod.GET)
	public List<MovieModel> getallMovies() {
		return service.listMovies();
	}
	
	@RequestMapping(value="/moviesPerGenre/{genre}", method = RequestMethod.GET)
	public List<MovieModel> getMoviesPerGenre(@PathVariable String genre) throws blankException {
		return service.listGenre(genre);
	}

}
