package com.moviesapplication.moviesApp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.moviesapplication.moviesApp.model.MovieModel;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

@Repository
public class MovieRepository implements RepositoryInt {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//save a new movie
	@Transactional
	public void saveMovie(MovieModel movie) {
		String procedure = "call INSERTARNUEVAPELICULA (?, ?)";
		jdbcTemplate.update(procedure, new Object[] {
				movie.getName(), movie.getDescription()
		});
	}
	
	//deleting a movie
	@Transactional
	public void deleteMovie(MovieModel movie) {
		String procedure = "call eliminarpelicula(?)";
		jdbcTemplate.update(procedure, movie.getName());
	}
	
	//editing a movie
	@Transactional
	public void editMovie(MovieModel movie) {
		String procedure = "call editarpelicula(?, ?, ?)";
		jdbcTemplate.update(procedure, new Object[] {
				movie.getId(), movie.getName(), movie.getDescription()
		});
	}
	
	//list all movies
	@Transactional
	public List<MovieModel> allMovies() {
		String sql = "select * from movies";
		
		List<MovieModel> movieList = jdbcTemplate.query(sql, new ResultSetExtractor<List<MovieModel>>() {
			@Override
			public List<MovieModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<MovieModel> list = new ArrayList<MovieModel>();
				while (rs.next()) {
					MovieModel movie = new MovieModel();
					movie.setId(rs.getInt("ID"));
					movie.setName(rs.getString("TITLE"));
					movie.setDescription(rs.getString("DSCR"));
					list.add(movie);
				}
				return list;
			}
		});
		return movieList;
	}
	
	//get movies per genre
	@Transactional
	public List<MovieModel> moviesPerGenre(String genre) {
		String sql = "select * from movies where dscr=" + "'" + genre + "'";
		List<MovieModel> movieList = jdbcTemplate.query(sql, new ResultSetExtractor<List<MovieModel>>() {
			@Override
			public List<MovieModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<MovieModel> list = new ArrayList<MovieModel>();
				while (rs.next()) {
					MovieModel movie = new MovieModel();
					movie.setId(rs.getInt("ID"));
					movie.setName(rs.getString("TITLE"));
					movie.setDescription(rs.getString("DSCR"));
					list.add(movie);
				}
				return list;
			}
		});
		return movieList;
	}

}
