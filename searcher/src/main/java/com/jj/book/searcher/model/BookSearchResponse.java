package com.jj.book.searcher.model;

import java.util.List;
import java.util.Map;

public class BookSearchResponse {
    private List<Map<String, Object>> bookList;
    private Map<String, Object> queryLog;

    public BookSearchResponse(List<Map<String, Object>> bookList, Map<String, Object> queryLog) {
        this.bookList = bookList;
        this.queryLog = queryLog;
    }

    public List<Map<String, Object>> getBookList() {
        return bookList;
    }

    public Map<String, Object> getQueryLog() {
        return queryLog;
    }

}