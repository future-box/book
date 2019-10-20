package com.jj.book.searcher.service;

import com.jj.book.searcher.config.SearchProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
    private final RestHighLevelClient restHighLevelClient;
    private final SearchProperty searchProperty;

    public String search(final String keyword) {
        Optional<SearchResponse> optionalSearchResponse;
        try {
            optionalSearchResponse = Optional.ofNullable(restHighLevelClient.search(this.searchRequest(keyword), RequestOptions.DEFAULT));
            if (optionalSearchResponse.isPresent()) {
                SearchResponse searchResponse = optionalSearchResponse.get();
                log.info("response: {}", searchResponse.toString());
                List<Map<String, Object>> sourceList = new ArrayList<>();
                for (SearchHit searchHit : searchResponse.getHits().getHits()) {
                    Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                    sourceList.add(sourceAsMap);
                };
                return sourceList.toString();
            }
        } catch (IOException e) {
            log.error(e.toString());
            throw new RuntimeException();
        }
        return "";
    }

    private SearchRequest searchRequest(final String keyword) {
        return new SearchRequest()
                .indices(searchProperty.getIndex())
                .source(this.searchSourceBuilder(keyword));
    }

    private SearchSourceBuilder searchSourceBuilder(final String keyword) {
        return new SearchSourceBuilder()
                .size(100)
                .query(this.queryBuilder(keyword));
    }

    private QueryBuilder queryBuilder(final String keyword) {
        return QueryBuilders.matchQuery("title", keyword);
    }

}