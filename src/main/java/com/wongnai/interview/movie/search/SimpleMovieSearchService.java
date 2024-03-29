package com.wongnai.interview.movie.search;

import java.util.ArrayList;
import java.util.List;

import com.wongnai.interview.movie.external.MovieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieSearchService;
import com.wongnai.interview.movie.external.MovieDataService;

@Component("simpleMovieSearchService")
public class SimpleMovieSearchService implements MovieSearchService {
	@Autowired
	private MovieDataService movieDataService;

	@Override
	public List<Movie> search(String queryText) {
		//TODO: Step 2 => Implement this method by using data from MovieDataService
		// All test in SimpleMovieSearchServiceIntegrationTest must pass.
		// Please do not change @Component annotation on this class

        //init return value
        ArrayList<Movie> movieResult  = new ArrayList<>();

		//search algorithm
		for (MovieData movieData : movieDataService.fetchAll()){
		    String[] titleSplit = movieData.getTitle().split(" ");

		    // for in titlesplit
            for(String splitText : titleSplit){
                if(splitText.toLowerCase().equals(queryText.toLowerCase())){
                    System.out.println(movieData.getTitle());
                    movieResult.add(new Movie(movieData.getTitle(), movieData.getCast()));
                }
            }
        }
		return movieResult;
	}
}
