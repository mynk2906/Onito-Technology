package com.Onito.io.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Rating {

	@Id
	private String tconst;
	private Float averageRating;
	private Integer numVotes;
	
	public Rating() {
	
	}

	public Rating(String tconst, Float averageRating, Integer numVotes) {
		super();
		this.tconst = tconst;
		this.averageRating = averageRating;
		this.numVotes = numVotes;
	}

	public String getTconst() {
		return tconst;
	}

	public void setTconst(String tconst) {
		this.tconst = tconst;
	}

	public Float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}

	public Integer getNumVotes() {
		return numVotes;
	}

	public void setNumVotes(Integer numVotes) {
		this.numVotes = numVotes;
	}
	
	
}
