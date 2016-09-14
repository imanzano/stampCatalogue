package com.imanzano.stamp.parser.colnect;

import com.imanzano.sc.common.parser.Parser;
import com.imanzano.sc.common.parser.html.HtmlParser;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.function.Function;

/**
 * Page parser with pagination
 */
public class PageParser<T> extends HtmlParser<PageInfo<T>> {

    private Parser<T> contentParser = null;
    private Function<Element,Integer> pageCountResolver = null;

    public PageParser(Parser<T> parser, Function<Element,Integer> pagesCount)
    {
        contentParser = parser;
        pageCountResolver = pagesCount;
        source(parser.getSource());
        //setProcessor(parser);
    }

    protected PageInfo<T> build(Element element) {

        final PageInfo<T> page = new PageInfo<>();
        //element.select(".pager_page:last-child").get(0).attr("href").split("/")

        page.pages = pageCountResolver.apply(element);

        try {
            final T content = contentParser.from(element).parse();
            page.content = content;
        } catch (final IOException e) {

        }

        return page;
    }
    protected String getSelectExpression() { return "body";}



}
