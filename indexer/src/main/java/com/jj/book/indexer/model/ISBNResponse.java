package com.jj.book.indexer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class ISBNResponse implements Serializable {
    private String PAGE_NO;
    private String TOTAL_COUNT;
    private List<Doc> docs;
}
