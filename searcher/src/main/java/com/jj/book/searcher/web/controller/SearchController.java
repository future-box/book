package com.jj.book.searcher.web.controller;

import com.jj.book.searcher.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search/{keyword}")
    public String search(@PathVariable String keyword) {
        return searchService.search(keyword);

    }

}
