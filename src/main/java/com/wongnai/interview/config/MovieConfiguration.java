package com.wongnai.interview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.TreeMap;

@Configuration
public class MovieConfiguration {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean("invertedIndexDatabase")
    public TreeMap invertedIndexDatabase(){
        TreeMap<String, Integer> wordMap = new TreeMap<String, Integer>( );

        return  wordMap;
    }
}
