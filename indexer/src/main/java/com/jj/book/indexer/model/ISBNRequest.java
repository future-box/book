package com.jj.book.indexer.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ISBNRequest implements Serializable {
    /**
     * cert_key	String(필수)		인증값
     * result_style	String(필수)		결과 형식 (json,xml)
     * page_no	String(필수)		현재 쪽번호(페이지 1부터 시작)
     * page_size	String(필수)		쪽당 출력건수
     * isbn	String	우절단 검색	ISBN
     * set_isbn	String	우절단 검색	SET ISBN
     * ebook_yn	String	일치검색	전자책여부 Y,N
     * title	String	형태소 + ngram	본표제
     * start_publish_date	String	범위검색	발행예정일
     * 시작(8자리, yyyymmdd)
     * end_publish_date	String	범위검색	발행예정일
     * 끝(8자리, yyyymmdd)
     * cip_yn	String	일치검색	CIP 신청여부 Y,N
     * series_title	String	형태소 + ngram	총서명
     * publisher	String	형태소 + ngram	발행처명
     * author	String	형태소 + ngram	저자
     * sort	String		정렬
     * PUBLISH_PREDATE
     * INPUT_DATE
     * INDEX_TITLE
     * INDEX_PUBLISHER
     * PUBLISH_PREDATE	String		정렬방식 ASC , DESC
     */
    private String certKey;
    private String resultStyle;
    private String pageNo;
    private String pageSize;
    private String startPublishDate;
    private String endPublishDate;
}