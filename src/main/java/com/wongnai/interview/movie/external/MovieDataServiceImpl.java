package com.wongnai.interview.movie.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class MovieDataServiceImpl implements MovieDataService {
	public static final String MOVIE_DATA_URL
			= "https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/movies.json";

	@Autowired
	private RestOperations restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public MoviesResponse fetchAll() {
		//TODO:
		// Step 1 => Implement this method to download data from MOVIE_DATA_URL and fix any error you may found.
		// Please noted that you must only read data remotely and only from given source,
		// do not download and use local file or put the file anywhere else.

		//init Object
		objectMapper = new ObjectMapper();
        URL url = null;
        try {
            url = new URL(MOVIE_DATA_URL);
            MovieData[] movieDataObjects = objectMapper.readValue(url, MovieData[].class);
            ArrayList<MovieData> movieDataArrayList = new ArrayList<MovieData>(Arrays.asList(movieDataObjects));
            MoviesResponse moviesResponse = new MoviesResponse(movieDataArrayList);
            return moviesResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
}
