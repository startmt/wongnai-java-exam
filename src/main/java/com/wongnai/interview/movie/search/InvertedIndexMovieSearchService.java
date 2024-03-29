package com.wongnai.interview.movie.search;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.MovieSearchService;

@Component("invertedIndexMovieSearchService")
@DependsOn("movieDatabaseInitializer")
public class InvertedIndexMovieSearchService implements MovieSearchService {
    @Autowired
    private MovieRepository movieRepository;

    @Qualifier("invertedIndexDatabase")
    @Autowired
    private Map<String, Set<Long>> wordMap;

    @Override
    public List<Movie> search(String queryText) {
        //TODO: Step 4 => Please implement in-memory inverted index to search movie by keyword.
        // You must find a way to build inverted index before you do an actual search. //success
        // Inverted index would looks like this:
        // -------------------------------
        // |  Term      | Movie Ids      |
        // -------------------------------
        // |  Star      |  5, 8, 1       |
        // |  War       |  5, 2          |
        // |  Trek      |  1, 8          |
        // -------------------------------
        // When you search with keyword "Star", you will know immediately, by looking at Term column, and see that
        // there are 3 movie ids contains this word -- 1,5,8. Then, you can use these ids to find full movie object from repository.
        // Another case is when you find with keyword "Star War", there are 2 terms, Star and War, then you lookup
        // from inverted index for Star and for War so that you get movie ids 1,5,8 for Star and 2,5 for War. The result that
        // you have to return can be union or intersection of those 2 sets of ids.
        // By the way, in this assignment, you must use intersection so that it left for just movie id 5.

        //init parameter
        String[] queryWords = queryText.toLowerCase().split(" ");
        ArrayList<Long> idList = new ArrayList<>();
        ArrayList<Long> intersecedList = new ArrayList<>();

        //fix bug can't find empty list
        intersecedList.add(null);

        //query word in Text
        for (String word : queryWords) {

            //add id's word in idList
            if(wordMap.containsKey(word)){
                wordMap.get(word).stream().forEach(id -> {
                    idList.add(id);
                });
            }
        }

        for (Long id : idList) {

            //intersection eg. "star", "war" is same "war", "star"
            if (idList.stream().filter(ids-> ids.equals(id)).count() == queryWords.length) {
                intersecedList.add(id);
            }
        }
        return movieRepository.getMovieByIdContains(intersecedList);
    }
}
