package com.jj.book.searcher.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jj.book.searcher.config.SearchProperty;
import com.jj.book.searcher.model.BookSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.disMaxQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
    private final RestHighLevelClient restHighLevelClient;
    private final SearchProperty searchProperty;
    private final Gson gson;

    public BookSearchResponse search(final String keyword) {
        List<Map<String, Object>> sourceList;

        SearchRequest searchRequest;
        try {
            searchRequest = this.searchRequest(keyword);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            log.info("response: {}", searchResponse.toString());
            sourceList = new ArrayList<>();
            for (SearchHit searchHit : searchResponse.getHits().getHits()) {
                Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                sourceList.add(sourceAsMap);
            }
        } catch (IOException e) {
            log.error(e.toString());
            throw new RuntimeException();
        }
        return new BookSearchResponse(sourceList, gson.fromJson(searchRequest.source().toString(), Map.class));
    }

    private SearchRequest searchRequest(final String keyword) {
        return new SearchRequest()
                .indices(searchProperty.getIndex())
                .source(this.searchSourceBuilder(keyword));
    }

    private SearchSourceBuilder searchSourceBuilder(final String keyword) {
        return new SearchSourceBuilder()
                .size(10000)
                .query(this.queryBuilder(keyword));
    }

    private QueryBuilder queryBuilder(final String keyword) {
        return disMaxQuery()
                .add(matchQuery("title", keyword).boost(2L).operator(Operator.AND))
                .add(matchQuery("author", keyword).operator(Operator.AND));
    }

}