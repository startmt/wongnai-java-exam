package com.wongnai.interview.movie.sync;

import javax.transaction.Transactional;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.external.MovieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.external.MovieDataService;

import java.util.ArrayList;

@Component
public class MovieDataSynchronizer {
	@Autowired
	private MovieDataService movieDataService;

	@Autowired
	private MovieRepository movieRepository;

	@Transactional
	public void forceSync() {
		initdatatodb();
		//TODO: implement this to sync movie into repository
	}

	private void initdatatodb() {
		ArrayList<MovieData> datas = movieDataService.fetchAll();
		datas.forEach(
				movieData -> movieRepository.save(new Movie(movieData.getTitle(), movieData.getCast()))
		);
	}
}
