package com.jj.book.indexer.service;

import com.jj.book.indexer.config.SearchProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckDocService {
    private final RestHighLevelClient restHighLevelClient;
    private final SearchProperty searchProperty;

    public boolean isDocByDateExist(String startDate, String endDate) {
        SearchRequest searchRequest = new SearchRequest()
                .indices(searchProperty.getIndex())
                .source(this.searchSourceBuilder(startDate, endDate));
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return searchResponse.getHits().getTotalHits().value == 0;
        } catch (IOException e) {
            log.error(e.toString());
            return false;
        }
    }

    private SearchSourceBuilder searchSourceBuilder(String startDate, String endDate) {
        return new SearchSourceBuilder()
                .size(0)
                .query(QueryBuilders.rangeQuery("publish_predate")
                        .from(startDate)
                        .to(endDate));
    }

}
