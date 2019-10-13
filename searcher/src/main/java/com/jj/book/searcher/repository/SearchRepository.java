package com.jj.book.searcher.repository;

import com.jj.book.searcher.model.ISBNRequest;
import lombok.AllArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class SearchRepository {
    private final RestHighLevelClient restHighLevelClient;

    public void save(ISBNRequest isbnRequest) {

    }

    public void findById(String id) {

    }

    public void findAll() {

    }

}