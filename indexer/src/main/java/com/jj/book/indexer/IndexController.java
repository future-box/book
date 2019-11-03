package com.jj.book.indexer;

import com.jj.book.indexer.model.ISBNRequest;
import com.jj.book.indexer.service.IndexingService;
import com.jj.book.indexer.validation.IndexValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class IndexController {
    private final IndexingService indexingService;

    @PostMapping("/index")
    public void index(@RequestBody @Valid final ISBNRequest isbnRequest) {
        indexingService.index(isbnRequest);
    }

}