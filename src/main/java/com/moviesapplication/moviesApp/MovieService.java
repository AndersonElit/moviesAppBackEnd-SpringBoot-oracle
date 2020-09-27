package com.moviesapplication.moviesApp;
import org.springframework.jdbc.core.JdbcTemplate;
import com.moviesapplication.moviesApp.MovieModel;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
	
	private JdbcTemplate jdbcTemplate;
	
	// JdbcTemplate setter
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//saving new movie
	public void saveMovie(MovieModel movie) {
		String procedure = "call INSERTARNUEVAPELICULA (?, ?)";
		jdbcTemplate.update(procedure, new Object[] {
				movie.getName(), movie.getDescription()
		});
	}

}
