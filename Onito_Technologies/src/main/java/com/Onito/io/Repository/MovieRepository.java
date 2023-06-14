package com.Onito.io.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Onito.io.Model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie,String> {
	

	@Query("SELECT m FROM Movie m JOIN Rating r ON m.tconst=r.tconst WHERE r.averageRating > 6.0")
	public List<Movie> RatinggreaterThan6();
	
	@Query("SELECT m.genres, SUM(r.numVotes) AS totalVotes "
            + "FROM Movie m JOIN Rating r ON m.tconst = r.tconst "
            + "GROUP BY m.genres")
    List<Object[]> GenreWithubtotals();
    
    @Modifying
    @Transactional
    @Query("UPDATE Movie m SET m.runtimeMinutes = "
            + "CASE "
            + "WHEN m.genres = 'Documentary' THEN m.runtimeMinutes + 15 "
            + "WHEN m.genres = 'Animation' THEN m.runtimeMinutes + 30 "
            + "ELSE m.runtimeMinutes + 45 "
            + "END")
    public void incrementRuntimeMinutes();
}
