package com.imanzano.sc.common.parser.html;

import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Support parse a Html page using Pagination
 */
public class HtmlPaginationParser<T> {

    private HtmlParser<T> parser = null;
    private Function<Integer,String> urlResolver = null;
    private Function<Element,Integer> pageCountResolver = null;

    /** Set Parser */
    public HtmlPaginationParser<T> using(HtmlParser<T> p)
    {
        parser = p;
        return this;
    }

    /** Set the function to resolver url of each page */
    public HtmlPaginationParser<T> pageUrlResolver(Function<Integer,String> resolver)
    {
        urlResolver = resolver;
        return this;
    }

    /** Set the function to resolver the total page count */
    public HtmlPaginationParser<T> pageCountResolver(Function<Element,Integer> resolver)
    {
        pageCountResolver = resolver;
        return this;
    }

    /**
     * Process all the pages and return the List<T>
     * @return A List<T> **/
    public List<T> process()  {

        final List<T> ret = new ArrayList<>();

        final PageParser<T> pageParser = new PageParser<>(parser,pageCountResolver);
        final PageInfo<T> process = pageParser.parse();
        ret.add(process.content);

        int page = 2;
        while (page <= process.pages) {

            final T result = parser.source(urlResolver.apply(page)).parse();
            ret.add(result);
            page++;
        }

        return ret;
    }
}
