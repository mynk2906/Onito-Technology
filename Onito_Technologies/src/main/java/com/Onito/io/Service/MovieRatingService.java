package com.Onito.io.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Onito.io.CSV.CsvFile;
import com.Onito.io.Exception.MovieException;
import com.Onito.io.Exception.RatingExeption;
import com.Onito.io.Model.GenreMovieSubtotal;
import com.Onito.io.Model.Movie;
import com.Onito.io.Model.Rating;
import com.Onito.io.Model.TopRatedMovie;
import com.Onito.io.Repository.MovieRepository;
import com.Onito.io.Repository.RatingRepository;

@Service
public class MovieRatingService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private RatingRepository ratingRepository;
	
	public void csvMovieFileLoader() throws IOException {
			
			CSVParser parser=CsvFile.csvMovieParse();
			
			for(CSVRecord record : parser) {

				Movie movie=new Movie(record.get("tconst"),record.get("titleType"),record.get("primaryTitle"),Integer.parseInt(record.get("runtimeMinutes")),record.get("genres"));
				movieRepository.save(movie);
				
			}
			parser.close();
		}
	

	public void csvRatingFileLoader() throws IOException {
		
		CSVParser parser=CsvFile.csvRatingParse();
		
		for(CSVRecord record : parser) {

			Float ar=Float.parseFloat(record.get("averageRating"));
			Integer nv=Integer.parseInt(record.get("numVotes"));
			Rating rating=new Rating(record.get("tconst"),ar,nv);
			
			ratingRepository.save(rating);
			
		}
		parser.close();
	}
	
	public List<Movie> longestDurationMovies() throws MovieException {
		
		List<Movie> movies= movieRepository.findAll();
		
		if(movies.isEmpty()) throw new MovieException("list is empty");
		
		movies.sort((a,b)->b.getRuntimeMinutes()-a.getRuntimeMinutes());
		
		int size = Math.min(movies.size(), 10);

		List<Movie> tenMovies = movies.subList(0, size); 
		
		return tenMovies;
		
	}
	
	public String newMovie(Movie movie) throws MovieException {
		
		Optional<Movie> opt= movieRepository.findById(movie.getTconst());
		
		if(opt.isPresent()) {
			throw new MovieException("movie id means tconst is already present");
		}
		
		movieRepository.save(movie);
		
		return "success";
	}
	
	public List<TopRatedMovie> topRatedMovies() throws RatingExeption, MovieException {
			
		List<Movie> movies= movieRepository.RatinggreaterThan6();
		
		if(movies.isEmpty()) throw new MovieException("list is empty");
		
		List<TopRatedMovie> topRatedMovies=new ArrayList<>();
		for(Movie m:movies) {
			Optional<Rating> optional=ratingRepository.findById(m.getTconst());
			
			if(!optional.isPresent()) throw new RatingExeption("tconst did not found in rating..");
			
			Rating rating=optional.get();
			
			TopRatedMovie ratedMovie=new TopRatedMovie();
			ratedMovie.setTconst(m.getTconst());
			ratedMovie.setPrimaryTitle(m.getPrimaryTitle());
			ratedMovie.setGenres(m.getGenres());
			ratedMovie.setAverageRating(rating.getAverageRating());
			
			topRatedMovies.add(ratedMovie);
			
		}
		
		return topRatedMovies;
		
	}
	
	public List<GenreMovieSubtotal> genreMovieWithSubtotal(){
		
		List<Object[]> genreWithubtotals = movieRepository.GenreWithubtotals();
		
		List<GenreMovieSubtotal> genrelist=new ArrayList<>();
		for (Object[] object : genreWithubtotals) {
		    String genre = (String) object[0];
		    Long totalVotes = (Long) object[1];
		    
		    GenreMovieSubtotal genreMovieSubtotal=new GenreMovieSubtotal();
		    genreMovieSubtotal.setGenre(genre);
		    genreMovieSubtotal.setTotal(totalVotes);
		    
		    genrelist.add(genreMovieSubtotal);
		    
		}
		
		return genrelist;
	}
	
	public List<Movie> updateRuntimeMinutes() throws MovieException{
		
		movieRepository.incrementRuntimeMinutes();
		
		List<Movie> movies= movieRepository.findAll();
		
		if(movies.isEmpty()) throw new MovieException("list is empty");
		
		return movies;
		
	}

}
