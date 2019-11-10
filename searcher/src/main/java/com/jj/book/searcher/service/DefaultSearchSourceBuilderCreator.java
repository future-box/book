package com.jj.book.searcher.service;

import com.jj.book.searcher.creator.SearchSourceBuilderCreator;
import com.jj.book.searcher.model.BookSearchRequest;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;

import static org.elasticsearch.index.query.QueryBuilders.disMaxQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

public class DefaultSearchSourceBuilderCreator extends SearchSourceBuilderCreator<BookSearchRequest> {

    @Override
    protected QueryBuilder queryBuilder(final BookSearchRequest bookSearchRequest) {
        String keyword = bookSearchRequest.getKeyword();
        return disMaxQuery()
                .add(matchQuery("title", keyword).boost(2L).operator(Operator.AND))
                .add(matchQuery("author", keyword).operator(Operator.AND));
    }

}