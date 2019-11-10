package com.jj.book.searcher.binder;

import com.jj.book.searcher.model.BookSearchRequest;

public class BookSearchRequestBinder {

    public void bind(final BookSearchRequest bookSearchRequest, final String keyword) {
        bookSearchRequest.setKeyword(keyword.trim());
    }

}
