package com.jj.book.indexer.filter;

import com.jj.book.indexer.model.Book;

import java.util.List;

@FunctionalInterface
public interface Filter {
    List<Book> filter(final List<Book> isbnResponse);
}
