package com.wongnai.interview.movie.external;

import java.util.ArrayList;

public class MoviesResponse extends ArrayList<MovieData> {
    public MoviesResponse(ArrayList<MovieData> movieDataArrayList) {
        addAll(movieDataArrayList);
    }
}
