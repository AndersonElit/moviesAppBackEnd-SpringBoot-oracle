package com.moviesapplication.moviesApp;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import com.moviesapplication.moviesApp.MovieService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;

@RestController
public class MovieController {
	
	@Autowired
	MovieService service;
	
	/*
	@RequestMapping(value="/movie/add/", method=RequestMethod.POST)
	public void addNewMovie(@RequestBody MovieModel movie) {
		service.saveMovie(movie);
	}
	*/
	
	@PostMapping(path="/movie/add/")
	public void addNewMovie(@RequestBody MovieModel movie) {
		service.saveMovie(movie);
		System.out.println("Pelicula guardada:");
		System.out.println(movie.getName() + " " + movie.getDescription());
	}

}
