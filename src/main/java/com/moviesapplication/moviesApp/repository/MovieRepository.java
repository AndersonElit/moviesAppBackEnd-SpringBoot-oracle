package com.moviesapplication.moviesApp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.moviesapplication.moviesApp.model.MovieModel;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

@Repository
public class MovieRepository implements RepositoryInt {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//save a new movie

	@Override
	public void saveMovie(MovieModel movie) {
		
		/*
		create or replace NONEDITIONABLE PROCEDURE NEWMOVIE 
		(
  			MOVNAME IN VARCHAR2 
		, GENTYPE IN VARCHAR2 
		) AS

		genexist NUMBER := 0;
		moviexist NUMBER := 0;
    
		BEGIN

    		SELECT COUNT(*) INTO genexist FROM genres
    		WHERE gendscr = gentype;
    
    	IF genexist = 0 THEN
        	INSERT INTO genres(gendscr) VALUES(gentype);
    	END IF;
    
    	SELECT COUNT(*) INTO moviexist FROM movies
    	WHERE name = movname;
    
    	IF moviexist = 0 THEN
        	INSERT INTO movies(name, genretype) VALUES(movname, gentype);
    	ELSE
        	DBMS_OUTPUT.PUT_LINE('La pelicula ya existe....');
    	END IF;
    
		END NEWMOVIE;
		*/
		
		String procedure = "CALL NEWMOVIE (?, ?)";
		jdbcTemplate.update(procedure, new Object[] {
				movie.getName(), movie.getDescription()
		});
	}
	
	//deleting a movie
	@Override
	public void deleteMovie(MovieModel movie) {
		
		/*
		CREATE OR REPLACE PROCEDURE DELMOVIE 
		(
  			MOVNAME IN VARCHAR2 
		) AS

		moviexist NUMBER := 0;

		BEGIN

  		SELECT COUNT(*) INTO moviexist FROM movies
  		WHERE name = movname;
  
  		IF moviexist > 0 THEN
    		DELETE FROM movies WHERE name = MOVNAME;
  		ELSE
    		DBMS_OUTPUT.PUT_LINE('La pelicula no existe....');
  		END IF;

		END DELMOVIE;
		*/
		
		String procedure = "CALL DELMOVIE(?)";
		jdbcTemplate.update(procedure, movie.getName());
	}
	
	//editing a movie
	@Override
	public void editMovie(MovieModel movie) {
		
		/*
		Stored procedure:
		
		create or replace NONEDITIONABLE PROCEDURE EDITARPELICULA 
		(
  			IDP IN NUMBER 
		, TITL IN VARCHAR2 
		, DESCR IN VARCHAR2 
		) AS 
		BEGIN
  			UPDATE movies
  			SET title = titl, dscr = descr
  			WHERE id = idp;
		END EDITARPELICULA;
		*/
		
		String procedure = "call editarpelicula(?, ?, ?)";
		jdbcTemplate.update(procedure, new Object[] {
				movie.getId(), movie.getName(), movie.getDescription()
		});	
	}
	
	//list all movies
	@Override
	public List<MovieModel> allMovies() {
		String sql = "select * from movies";
		
		List<MovieModel> movieList = jdbcTemplate.query(sql, new ResultSetExtractor<List<MovieModel>>() {
			@Override
			public List<MovieModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<MovieModel> list = new ArrayList<MovieModel>();
				while (rs.next()) {
					MovieModel movie = new MovieModel();
					movie.setId(rs.getInt("ID"));
					movie.setName(rs.getString("NAME"));
					movie.setDescription(rs.getString("GENRETYPE"));
					list.add(movie);
				}
				return list;
			}
		});
		return movieList;
	}
	
	//get movies per genre
	@Override
	public List<MovieModel> moviesPerGenre(String genre) {
		String sql = "select * from movies where dscr=" + "'" + genre + "'";
		List<MovieModel> movieList = jdbcTemplate.query(sql, new ResultSetExtractor<List<MovieModel>>() {
			@Override
			public List<MovieModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<MovieModel> list = new ArrayList<MovieModel>();
				while (rs.next()) {
					MovieModel movie = new MovieModel();
					movie.setId(rs.getInt("ID"));
					movie.setName(rs.getString("NAME"));
					movie.setDescription(rs.getString("GENRETYPE"));
					list.add(movie);
				}
				return list;
			}
		});
		return movieList;
	}

}
