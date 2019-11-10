package com.jj.book.searcher.service;

import com.jj.book.searcher.creator.SearchSourceBuilderCreator;
import com.jj.book.searcher.model.BookSearchRequest;
import org.elasticsearch.index.query.QueryBuilder;

public class NewBookSearchSourceBuilderCreator extends SearchSourceBuilderCreator<BookSearchRequest> {

    @Override
    protected QueryBuilder queryBuilder(BookSearchRequest bookSearchRequest) {
        return null;
    }

}
