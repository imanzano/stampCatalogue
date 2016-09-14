package com.imanzano.stamp.parser.colnect;

import com.imanzano.sc.common.parser.html.ElementConverter;
import com.imanzano.sc.common.parser.html.ElementProcessor;
import com.imanzano.sc.common.parser.html.HtmlParser;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.function.Function;

/**
 * Page parser with pagination
 */
public class PageParser<T> extends HtmlParser<PageInfo<T>> {

    private HtmlParser<T> contentParser = null;
    private Function<Element,Integer> pageCountResolver = null;

    public PageParser(HtmlParser<T> parser, Function<Element,Integer> pagesCount)
    {
        contentParser = parser;
        pageCountResolver = pagesCount;
        source(parser.getSource());
        setProcessor(new ElementProcessor<>(getConverter()).using(getSelectExpression()));
    }

    protected ElementConverter<PageInfo<T>> getConverter() {
        return e -> {
            final PageInfo<T> page = new PageInfo<>();
            //element.select(".pager_page:last-child").get(0).attr("href").split("/")

            page.pages = pageCountResolver.apply(e);

            try {
                final T content = contentParser.from(e).parse();
                page.content = content;
            } catch (final IOException ignored) {

            }

            return page;
        };
    }

    protected String getSelectExpression() { return "body";}
}
