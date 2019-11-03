package com.jj.book.indexer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jj.book.indexer.config.SearchProperty;
import com.jj.book.indexer.model.Doc;
import com.jj.book.indexer.model.ISBNRequest;
import com.jj.book.indexer.model.ISBNResponse;
import com.mchange.util.AssertException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class IndexingService {
    private final RestHighLevelClient restHighLevelClient;
    private final RestTemplateBuilder restTemplateBuilder;
    private final ObjectMapper objectMapper;
    private final Gson gson;
    private final SearchProperty searchProperty;
    private final ITBookFilter itBookFilter;

    // open api 요청 날리고 응답
    public void index(final ISBNRequest isbnRequest) {
        Map<String, String> urlVariables = objectMapper.convertValue(isbnRequest, new TypeReference<Map<String, String>>() {});
        log.info("[urlVariables]: " + urlVariables.toString());

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String> response = restTemplate.getForEntity(this.searchProperty.getUrl(), String.class, urlVariables);
        ISBNResponse isbnResponse;
        if (response.getStatusCode() == HttpStatus.OK) {
            String body = response.getBody();
            assert body != null;
            isbnResponse = gson.fromJson(body.toLowerCase(), ISBNResponse.class);
            log.debug("[response]: " + isbnResponse.toString());
            this.save(isbnResponse);
        }
    }

    // es 적재
    private void save(final ISBNResponse isbnResponse) {
        //서적 정보 Doc -> IndexRequest
        Stream<IndexRequest> indexRequestStream = isbnResponse.getDocs().stream()
                .filter(itBookFilter)
                .map(response -> new IndexRequest()
                        .index("book")
                        .id(response.getEa_isbn())
                        .source(objectMapper.convertValue(response, Map.class)));
        IndexRequest[] indexRequests = indexRequestStream.toArray(IndexRequest[]::new);

        BulkRequest bulkRequest = new BulkRequest().add(indexRequests);
        try {
            BulkResponse response = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            log.info("ES response: {}", response.toString());
        } catch (IOException e) {
            log.error(e.toString());
        }
    }

}
