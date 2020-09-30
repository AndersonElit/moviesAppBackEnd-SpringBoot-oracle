package com.moviesapplication.moviesApp;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MovieController {
	
	@Autowired
	private MovieRepository movieRepo;
	
	@RequestMapping(value="/addMovie", method = RequestMethod.POST)
	public void addMovie(@RequestBody MovieModel movie) {
		movieRepo.saveMovie(movie);
		System.out.println("se ingreso la pelicula");
	}
	
	@RequestMapping(value="/deleteMovie", method = RequestMethod.POST)
	public void deleteMovie(@RequestBody MovieModel movie) {
		movieRepo.deleteMovie(movie);
		System.out.println("se elimino la pelicula");
	}
	
	@RequestMapping(value="/editMovie", method = RequestMethod.POST)
	public void editMovie(@RequestBody MovieModel movie) {
		movieRepo.editMovie(movie);
		System.out.println("se edito la pelicula");
	}
	
	@RequestMapping(value="/allMovies", method = RequestMethod.GET)
	public List<MovieModel> getallMovies() {
		return movieRepo.allMovies();
	}
	
	@RequestMapping(value="/moviesPerGenre", method = RequestMethod.GET)
	public List<MovieModel> getMoviesPerGenre() {
		return movieRepo.moviesPerGenre("accion");
	}

}
