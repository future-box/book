package com.jj.book.searcher.service;

import com.google.gson.Gson;
import com.jj.book.searcher.creator.SearchRequestCreator;
import com.jj.book.searcher.model.BookSearchRequest;
import com.jj.book.searcher.model.BookSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchRequestCreator<BookSearchRequest> searchRequestCreator;
    private final RestHighLevelClient restHighLevelClient;
    private final Gson gson;

    public BookSearchResponse search(final BookSearchRequest bookSearchRequest) {
        List<Map<String, Object>> sourceList;
        SearchRequest searchRequest;
        try {
            searchRequest = this.searchRequestCreator.create(bookSearchRequest);
            SearchResponse searchResponse = this.restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            log.info("response: {}", searchResponse.toString());
            sourceList = Arrays.stream(searchResponse.getHits().getHits())
                    .map(SearchHit::getSourceAsMap)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error(e.toString());
            throw new RuntimeException();
        }
        return new BookSearchResponse(sourceList, this.gson.fromJson(searchRequest.source().toString(), Map.class));
    }

}