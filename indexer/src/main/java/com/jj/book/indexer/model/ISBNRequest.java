package com.jj.book.indexer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class ISBNRequest implements Serializable {
    @NotEmpty
    private String certKey = "a10b2871db69804570b63feebda28ca6";
    private String resultStyle = "json";
    @Range(min = 1L, max = 999L)
    private String pageNo = "1";
    @Range(min = 1L, max = 999L)
    private String pageSize = "999";
    @Length(min = 8, max = 8)
    private String startPublishDate;
    @Length(min = 8, max = 8)
    private String endPublishDate;
}