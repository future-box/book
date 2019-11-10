package com.jj.book.searcher.creator;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;


public abstract class SearchSourceBuilderCreator<T> {

    SearchSourceBuilder create(final T t) {
        return new SearchSourceBuilder()
                .size(10)
                .query(this.queryBuilder(t));
    }

    protected abstract QueryBuilder queryBuilder(final T t);

}
