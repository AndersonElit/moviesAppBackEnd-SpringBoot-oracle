package com.moviesapplication.moviesApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moviesapplication.moviesApp.model.MovieModel;
import com.moviesapplication.moviesApp.repository.MovieRepository;

@Service
public class MovieService implements ServiceInt {
	
	@Autowired
	private MovieRepository movieRepo;

	@Transactional
	public void addMovie(MovieModel movie) {
		movieRepo.saveMovie(movie);
		System.out.println("se agrego una nueva pelicula");
		
	}

	@Transactional
	public void delMovie(MovieModel movie) {
		movieRepo.deleteMovie(movie);
		System.out.println("se elimino la pelicula");
	}

	@Transactional
	public void edMovie(MovieModel movie) {
		movieRepo.editMovie(movie);
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