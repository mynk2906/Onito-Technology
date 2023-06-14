package com.Onito.io.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Onito.io.Exception.MovieException;
import com.Onito.io.Exception.RatingExeption;
import com.Onito.io.Model.GenreMovieSubtotal;
import com.Onito.io.Model.Movie;
import com.Onito.io.Model.TopRatedMovie;
import com.Onito.io.Service.MovieRatingService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/v1")
public class MovieRatingController {

	@Autowired
	private MovieRatingService movieRatingService;
	
	@PostConstruct
	public void moviePostConstruct() throws IOException {
		
		movieRatingService.csvMovieFileLoader();
		movieRatingService.csvRatingFileLoader();
	}
	
	@GetMapping("/longest-duration-movies")
	public ResponseEntity<List<Movie>> longestDurationMoviesHandler() throws MovieException{
		
		List<Movie> movies= movieRatingService.longestDurationMovies();
		
		return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);
		
	}
	
	@PostMapping("/new-movie")
	public ResponseEntity<String> newMovieHandler(@RequestBody Movie movie) throws MovieException{
		
		String s=movieRatingService.newMovie(movie);
		return new ResponseEntity<String>(s,HttpStatus.CREATED);
	}
	
	@GetMapping("/top-rated-movies")
	public ResponseEntity<List<TopRatedMovie>> topRatedMoviesHandler() throws RatingExeption, MovieException{
		
		List<TopRatedMovie> movies= movieRatingService.topRatedMovies();
		
		return new ResponseEntity<>(movies,HttpStatus.OK);
		
	}
	
	@GetMapping("/genre-movies-with-subtotals")
	public ResponseEntity<List<GenreMovieSubtotal>> genereMoviesHandler() throws RatingExeption{
		
		List<GenreMovieSubtotal> gList= movieRatingService.genreMovieWithSubtotal();
		
		return new ResponseEntity<>(gList,HttpStatus.OK);
		
	}	
	
	@PostMapping("/update-runtime-minutes")
	public ResponseEntity<List<Movie>> updateRunTimeMinutesHandler() throws MovieException{
		
		List<Movie> list=movieRatingService.updateRuntimeMinutes();
		return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
	}
	
}
