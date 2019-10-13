package com.jj.book.searcher.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jj.book.searcher.model.ISBNRequest;
import com.jj.book.searcher.model.ISBNResponse;
import lombok.AllArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;

@Repository
@AllArgsConstructor
public class SearchRepository {
    private final RestHighLevelClient restHighLevelClient;
    private final ObjectMapper objectMapper;

    public void save(ISBNResponse isbnResponse) throws IOException {
        IndexRequest indexRequest = new IndexRequest()
                .index("book")
                .type("doc")
                .source(objectMapper.convertValue(isbnResponse, Map.class));
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    public void findById(String id) {

    }

    public void findAll() {

    }

}