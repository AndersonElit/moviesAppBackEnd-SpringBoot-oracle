package com.moviesapplication.moviesApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moviesapplication.moviesApp.exception.blankException;
import com.moviesapplication.moviesApp.model.MovieModel;
import com.moviesapplication.moviesApp.repository.MovieRepository;

@Service
public class MovieService implements ServiceInt {
	
	@Autowired
	private MovieRepository movieRepo;

	@Transactional
	public void addMovie(MovieModel movie) throws blankException {
		
		if(movie.getName().isBlank() || movie.getDescription().isBlank()) {
			
			throw new blankException("Debes ingresar todos los items.....");
			
		} else {
			
			movieRepo.saveMovie(movie);
			System.out.println("se agrego una nueva pelicula");
			
		}
		
	}

	@Transactional
	public void delMovie(String movName) {
			
		movieRepo.deleteMovie(movName);
		System.out.println("se elimino la pelicula");
		
	}

	@Transactional
	public void edMovie(int id, String name, String description, String actor, float income) throws blankException {
			
			movieRepo.editMovie(id, name, description, actor, income);
			System.out.println("se edito la pelicula");
		
	}

	@Transactional
	public List<MovieModel> listMovies() {
		return movieRepo.allMovies();
	}

	@Transactional
	public List<MovieModel> listGenre(String genre) {
		
		return movieRepo.moviesPerGenre(genre);
		
	}

}
