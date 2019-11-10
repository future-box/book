package com.jj.book.searcher.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class BookSearchRequest {
    @Setter
    private String keyword;
    private String category;
    private String searchType;
}
