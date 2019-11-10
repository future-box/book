package com.jj.book.searcher.web.controller;

import com.jj.book.searcher.binder.BookSearchRequestBinder;
import com.jj.book.searcher.model.BookSearchRequest;
import com.jj.book.searcher.model.BookSearchResponse;
import com.jj.book.searcher.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;
    private final BookSearchRequestBinder bookSearchRequestBinder;


    @GetMapping("/search/{keyword}")
    public BookSearchResponse search(@PathVariable final String keyword, @ModelAttribute final BookSearchRequest bookSearchRequest) {
        bookSearchRequestBinder.bind(bookSearchRequest, keyword);
        return searchService.search(bookSearchRequest);
    }

}
