package com.jj.book.searcher.creator;

import com.jj.book.searcher.config.SearchProperty;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchRequestCreator<T> {
    private final SearchProperty searchProperty;
    private final SearchSourceBuilderCreator<T> newBookSearchSourceBuilderCreator;

    public SearchRequest create(final T t) {
        return new SearchRequest()
                .indices(this.searchProperty.getIndex())
                .source(this.newBookSearchSourceBuilderCreator.create(t));
    }

}