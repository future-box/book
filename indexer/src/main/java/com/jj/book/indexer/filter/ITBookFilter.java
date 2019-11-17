package com.jj.book.indexer.filter;

import com.jj.book.indexer.model.Book;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Predicate;

@Component
public class ITBookFilter implements Predicate<Book> {

    @Override
    public boolean test(Book book) {
        Analyzer analyzer = new WhitespaceAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("title", book.getTitle());
        CharTermAttribute termAttribute = tokenStream.getAttribute(CharTermAttribute.class);
        try {
            tokenStream.reset();
            while (true) {
                if (!tokenStream.incrementToken())
                    break;
                for (Keyword keyword : Keyword.values()) {
                    if (keyword.name().toLowerCase().equals(termAttribute.toString()))
                        return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}