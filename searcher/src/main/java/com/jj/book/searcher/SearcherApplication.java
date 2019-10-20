package com.jj.book.searcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SearcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearcherApplication.class, args);
    }

}
