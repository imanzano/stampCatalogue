package com.imanzano.sc.common.parser.html;

import org.jsoup.nodes.Element;

import java.util.function.Function;

/**
 * Page parser with pagination
 */
class PageParser<T> extends HtmlParser<PageInfo<T>> {

    private HtmlParser<T> contentParser = null;
    private Function<Element,Integer> pageCountResolver = null;

    PageParser(HtmlParser<T> parser, Function<Element, Integer> pagesCount)
    {
        contentParser = parser;
        pageCountResolver = pagesCount;
        source(parser.getSource());
        setProcessor(new ElementProcessor<>(getConverter()).using(getSelectExpression()));
    }

    private ElementConverter<PageInfo<T>> getConverter() {
        return e -> {
            final PageInfo<T> page = new PageInfo<>();

            page.pages = pageCountResolver.apply(e);
            page.content = contentParser.from(e).parse();
            return page;
        };
    }

    private String getSelectExpression() { return "body";}
}
