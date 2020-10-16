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
		stored procedure to add a new movie in oracle:
		
		create or replace NONEDITIONABLE PROCEDURE NEWMOVIE 
		(
  			MOVNAME IN VARCHAR2 
		, GENTYPE IN VARCHAR2
		, ACTOR IN VARCHAR2
		, INCOMEVAR IN FLOAT
		) AS

		genexist NUMBER := 0;
		moviexist NUMBER := 0;
		actorexist NUMBER := 0;

		CURSOR c_income IS
    		SELECT income FROM movies WHERE actors = actor;

		TYPE c_list IS TABLE OF movies.income%TYPE INDEX BY BINARY_INTEGER;
		income_list c_list;
		counter INTEGER := 0;
		sumall NUMBER := 0;
    
		BEGIN

    		SELECT COUNT(*) INTO genexist FROM genres
    		WHERE gendscr = gentype;
    
    		SELECT COUNT(*) INTO moviexist FROM movies
    		WHERE name = movname;
    
    		SELECT COUNT(*) INTO actorexist FROM actors
    		WHERE name = actor;
    
    		IF genexist = 0 THEN
        		INSERT INTO genres(gendscr) VALUES(gentype);
    		END IF;
    
    		IF actorexist = 0 THEN
    
        		INSERT INTO actors(name, netincome) VALUES(actor, incomevar);
        
    		ELSIF actorexist > 0 THEN
    
        		FOR n IN c_income LOOP
            		counter := counter + 1;
            		income_list(counter) := n.income;
            		sumall := sumall + income_list(counter);
        		END LOOP;
        
        		IF moviexist = 0 THEN
            		sumall := sumall + incomevar;
        		END IF;
        
        		--update value netincome
        		UPDATE actors SET netincome = sumall WHERE name = actor;
        
    		END IF;
    
    		IF moviexist = 0 THEN
        		INSERT INTO movies(name, genretype, actors, income) VALUES(movname, gentype, actor, incomevar);
    		ELSE
        		DBMS_OUTPUT.PUT_LINE('La pelicula ya existe....');
    		END IF;
    
		END NEWMOVIE;
		*/
		
		String procedure = "CALL NEWMOVIE (?, ?, ?, ?)";
		jdbcTemplate.update(procedure, new Object[] {
				movie.getName(), movie.getDescription(), movie.getActor(), movie.getIncome()
		});
	}
	
	//deleting a movie
	@Override
	public void deleteMovie(String movName) {
		
		/*
		stored procedure to delete a movie:
		
		create or replace NONEDITIONABLE PROCEDURE DELMOVIE 
		(
  			MOVNAME IN VARCHAR2 
		) AS

		moviexist NUMBER := 0;
		genrecount NUMBER := 0;
		actorcount NUMBER := 0;
		gentype movies.genretype%TYPE;
		actorname movies.actors%TYPE;

		BEGIN
  
  		SELECT COUNT(*) INTO moviexist FROM movies
  		WHERE name = movname;
  
  		IF moviexist > 0 THEN
    
    		--extract genre and actor
    		SELECT genretype, actors INTO gentype, actorname FROM movies
    		WHERE name = movname;
    
    		--how many times appear genre an actor in movies table
    		SELECT COUNT(*) INTO genrecount FROM movies
    		WHERE genretype = gentype;
    
    		SELECT COUNT(*) INTO actorcount FROM movies
    		WHERE actors = actorname;
    
    		--Delete movie
    		DELETE FROM movies WHERE name = MOVNAME;
    
    		--delete genre and actor if these doesn´t appear in other items
    		IF genrecount = 1 THEN
        		DELETE FROM genres WHERE gendscr = gentype;
    		END IF;
    
    		IF actorcount = 1 THEN
        		DELETE FROM actors WHERE name = actorname;
    		END IF;
    
  		ELSE
  
    		DBMS_OUTPUT.PUT_LINE('La pelicula no existe....');
    
  		END IF;

		END DELMOVIE;
		*/
		
		String procedure = "CALL DELMOVIE(?)";
		jdbcTemplate.update(procedure, movName);
	}
	
	//editing a movie
	@Override
	public void editMovie(int id, String name, String description, String actor, float income) {
		
		/*
		Stored procedure:
		
		create or replace NONEDITIONABLE PROCEDURE EDITARPELICULA 
		(
  			IDP IN NUMBER 
		, TITL IN VARCHAR2 
		, DESCR IN VARCHAR2
		, ACTORNAME IN VARCHAR2
		, INCOMEACTOR IN FLOAT
		) AS 
		BEGIN
  			UPDATE movies
  			SET name = titl, genretype = descr, actors = actorname, income= incomeactor
  			WHERE id = idp;
		END EDITARPELICULA;
		*/
		
		String procedure = "call editarpelicula(?, ?, ?, ?, ?)";
		jdbcTemplate.update(procedure, new Object[] {
				id, name, description, actor, income
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
					movie.setActor(rs.getString("ACTORS"));
					movie.setIncome(rs.getFloat("INCOME"));
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
					movie.setActor(rs.getString("ACTORS"));
					movie.setIncome(rs.getFloat("INCOME"));
					list.add(movie);
				}
				return list;
			}
		});
		return movieList;
	}

}
