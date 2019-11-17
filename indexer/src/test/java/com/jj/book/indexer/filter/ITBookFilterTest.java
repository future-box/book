package com.jj.book.indexer.filter;

import com.jj.book.indexer.model.Book;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ITBookFilterTest {

    @Test
    public void test1() {
        Book book = new Book();
        book.setTitle("이것이 java");
        ITBookFilter bookFilter = new ITBookFilter();
        Assert.assertTrue(bookFilter.test(book));
    }
}