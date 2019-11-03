package com.jj.book.indexer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Doc {
    private String title;
    private String vol;
    private String series_title;
    private String series_no;
    private String author;
    private String ea_isbn;
    private String ea_add_code;
    private String set_isbn;
    private String set_add_code;
    private String set_expression;
    private String publisher;
    private String edition_stmt;
    private String pre_price;
    private String kdc;
    private String ddc;
    private String page;
    private String book_size;
    private String form;
    private String publish_predate;
    private String subject;
    private String ebook_yn;
    private String cip_yn;
    private String control_no;
    private String title_url;
    private String book_tb_cnt_url;
    private String book_introduction_url;
    private String book_summary_url;
    private String publisher_url;
    private String input_date;
    private String update_date;
}
