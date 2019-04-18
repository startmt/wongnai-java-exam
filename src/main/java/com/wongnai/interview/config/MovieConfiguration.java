package com.wongnai.interview.config;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class MovieConfiguration {
    @Autowired
    private MovieRepository movieRepository;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean("invertedIndexDatabase")
    public Map<String, Set<Long>> invertedIndexDatabase(){
        Map<String, Set<Long>> wordMap = new HashMap<String,Set<Long>>( );
        ArrayList<Movie> movieArrayList = new ArrayList<>(movieRepository.getAllMovie());

        for (Movie movie : movieArrayList){
            //get word in movie
            String[] wordInmovie = movie.getName().toLowerCase().split(" ");
            //search word in Map
            for (String word : wordInmovie){
                //if word has not in map >> add new Map
                if (!wordMap.containsKey(word)){
                    wordMap.put(word, new HashSet<>());
                }
                //if word has in map >> add id in all map
                wordMap.get(word).add(movie.getId());
            }
        }
        System.out.println(wordMap);
        return  wordMap;
    }
}
