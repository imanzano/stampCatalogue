package com.imanzano.stamp.parser.colnect;

import com.imanzano.sc.common.parser.Parser;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Support parse a Html page using Pagination
 */
public class HtmlPaginationParser<T> {

    private Parser<T> parser = null;
    private Function<Integer,String> urlResolver = null;
    private Function<Element,Integer> pageCountResolver = null;

    /** Set Parser */
    public HtmlPaginationParser<T> using(Parser<T> p)
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
    public List<T> process() throws IOException {

        int page = 1;
        final List<T> ret = new ArrayList<>();

        final PageParser<T> pageParser = new PageParser<>(parser,pageCountResolver);
        final PageInfo<T> process = pageParser.parse();

        while (page <= process.pages) {

            final T result = parser.source(urlResolver.apply(page)).parse();
            ret.add(result);
            page++;
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {

        final HtmlPaginationParser<List<Stamp>> pParser = new HtmlPaginationParser<>();

        final StampPageParser stampPageParser = new StampPageParser("http://colnect.com/en/stamps/list/country/15824-Abu_Dhabi/year/1972");
        final List<List<Stamp>> process = pParser.using(stampPageParser)
                                                .pageCountResolver(integer -> 2)
                                                .pageUrlResolver(integer -> "/page/" + integer)
                                                .process();
        process.toString();
    }
}
