package com.jj.book.indexer.service;

import com.jj.book.indexer.model.Doc;

import java.util.List;

@FunctionalInterface
public interface Filter {
    List<Doc> filter(final List<Doc> isbnResponse);
}
