package com.jj.book.indexer.service;

import com.jj.book.indexer.model.Doc;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class ITBookFilter implements Predicate<Doc> {


    @Override
    public boolean test(Doc doc) {
        return true;
    }
}