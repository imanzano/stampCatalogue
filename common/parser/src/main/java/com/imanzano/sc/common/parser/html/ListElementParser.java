package com.imanzano.sc.common.parser.html;

import org.jsoup.nodes.Element;

import java.util.List;

/**
 * Parse a List of Element and return a List<T>
 */
public abstract class ListElementParser<T> extends HtmlParser<List<T>> {

    /** @return Url */
    protected String getUrl() { return "";}

    /** build an element T from Element */
    protected T build(Element element) { return  null;}

    /** @return the expression to use to extract the elements from the html */
    protected String getSelectExpression() { return "";}

    /** Default constructor */
    protected ListElementParser()
    {
        final ElementParser<List<T>> parser = new ListItemParser<>(new ElementParser<T>() {

            @Override
            protected T evaluate(Element element) {
                return build(element);
            }
        }).using(getSelectExpression());

        setContentParser(parser);
        source(getUrl());
    }
}
