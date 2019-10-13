package com.jj.book.searcher.web.controller;

import com.jj.book.searcher.model.ISBNRequest;
import com.jj.book.searcher.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @PostMapping("/index")
    public void index(@RequestBody final ISBNRequest isbnRequest) {
        searchService.index(isbnRequest);
    }

}
