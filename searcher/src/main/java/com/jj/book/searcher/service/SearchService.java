package com.jj.book.searcher.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jj.book.searcher.model.ISBNRequest;
import com.jj.book.searcher.model.ISBNResponse;
import com.jj.book.searcher.repository.SearchRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class SearchService {
    private final RestTemplateBuilder restTemplateBuilder;
    private final ObjectMapper objectMapper;
    private final Gson gson;
    private final SearchRepository searchRepository;
    private final String URL = "http://seoji.nl.go.kr/landingPage/SearchApi.do" +
            "?cert_key={certKey}&result_style={resultStyle}&page_size={pageSize}&page_no={pageNo}&start_publish_date={startPublishDate}&end_publish_date={endPublishDate}";

    public void index(final ISBNRequest isbnRequest) {
        Map<String, String> urlVariables = objectMapper.convertValue(isbnRequest, Map.class);
        log.info("[urlVariables]: " + urlVariables.toString());
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String> response = restTemplate.getForEntity(this.URL, String.class, urlVariables);
        ISBNResponse isbnResponse;
        if (response.getStatusCode() == HttpStatus.OK) {
            isbnResponse = gson.fromJson(response.getBody(), ISBNResponse.class);
            log.info("[response]:" + isbnResponse.toString());
        }
    }

}
