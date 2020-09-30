package com.moviesapplication.moviesApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MovieRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	public void saveMovie(MovieModel movie) {
		String procedure = "call INSERTARNUEVAPELICULA (?, ?)";
		jdbcTemplate.update(procedure, new Object[] {
				movie.getName(), movie.getDescription()
		});
	}
	
	@Transactional
	public void deleteMovie(MovieModel movie) {
		String procedure = "call eliminarpelicula(?)";
		jdbcTemplate.update(procedure, movie.getName());
	}
	
	@Transactional
	public void editMovie(MovieModel movie) {
		String procedure = "call editarpelicula(?, ?, ?)";
		jdbcTemplate.update(procedure, new Object[] {
				movie.getId(), movie.getName(), movie.getDescription()
		});
	}

}
