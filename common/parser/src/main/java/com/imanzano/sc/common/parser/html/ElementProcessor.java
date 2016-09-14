package com.imanzano.sc.common.parser.html;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static java.lang.String.format;

/**
 * Element processor
 */
public class ElementProcessor<T> {

    private String expression = null;
    private ElementConverter<T> converter = null;

    /** */
    public ElementProcessor() {}

    /** */
    ElementProcessor(ElementConverter<T> c)
    {
        converter  = c;
    }

    String getExpression()
    {
        return expression;
    }

    protected T process(Element element) throws IOException{

        final Elements elements = element.select(getExpression());

        if (elements == null || elements.isEmpty()) return null;
        if (elements.size()> 1 ) throw new IllegalStateException(format("More than 1 result was found using expression %s",getExpression()));

        return converter.convert(elements.first());
    }

    /** @param e Css Expression to use */
    ElementProcessor<T> using(String e)
    {
        expression = e;
        return this;
    }
}
