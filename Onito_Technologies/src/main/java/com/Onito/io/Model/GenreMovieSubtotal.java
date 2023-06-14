package com.Onito.io.Model;

public class GenreMovieSubtotal {
	
	private String Genre;
	private Long total;
	
	public GenreMovieSubtotal() {
	
	}

	public GenreMovieSubtotal(String genre, Long total) {
		super();
		Genre = genre;
		this.total = total;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	
	
	
}
