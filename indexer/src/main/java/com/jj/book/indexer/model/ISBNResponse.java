package com.jj.book.indexer.model;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

public class ISBNResponse implements Serializable {
    private List<Book> docs;
    private int total_count;

    public List<Book> getBooks() {
        return docs;
    }

    public void setBooks(List<Book> docs) {
        this.docs = docs;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ISBNResponse.class.getSimpleName() + "[", "]")
                .add("docs=" + docs)
                .toString();
    }

    public void setTotal_count(int totalCount) {
        this.total_count = totalCount;
    }

    public int getTotal_count() {
        return this.total_count;
    }

}
