package com.jj.book.indexer;

import com.jj.book.indexer.model.ISBNRequest;
import com.jj.book.indexer.service.IndexingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final IndexingService indexingService;

    @PostMapping("/index")
    public void index(@RequestBody final ISBNRequest isbnRequest) {
        indexingService.index(isbnRequest);
    }

}