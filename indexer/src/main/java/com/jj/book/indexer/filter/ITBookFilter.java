package com.jj.book.indexer.filter;

import com.jj.book.indexer.model.Book;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class ITBookFilter implements Predicate<Book> {

    @Override
    public boolean test(Book book) {
        return true;
    }

}