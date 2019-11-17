package com.jj.book.indexer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jj.book.indexer.config.SearchProperty;
import com.jj.book.indexer.model.ISBNRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class IndexingServiceTest {
    @Autowired
    private IndexingService indexingService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SearchProperty searchProperty;

    @Test
    public void index() {
        // given
        ISBNRequest isbnRequest = new ISBNRequest();
        isbnRequest.setStartPublishDate("20190101");
        isbnRequest.setEndPublishDate("20190231");

        Map<String, String> urlVariables = this.objectMapper.convertValue(isbnRequest, new TypeReference<Map<String, String>>() {
        });
        ResponseEntity<String> response = this.restTemplate.getForEntity(this.searchProperty.getUrl(), String.class, urlVariables);
        System.out.println(response.getBody());
    }

}