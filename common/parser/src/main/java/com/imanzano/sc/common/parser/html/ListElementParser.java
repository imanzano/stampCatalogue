package com.imanzano.sc.common.parser.html;

import java.util.List;

/**
 * Parse a List of Element and return a List<T>
 */
public class ListElementParser<T> extends HtmlParser<List<T>> {

    /** Default constructor */
    protected ListElementParser()
    {
        setProcessor(new ListElementProcessor<>(getConverter()).using(getSelectExpression()));
        source(getUrl());
    }

    /** @return Url */
    protected String getUrl() {  throw new IllegalStateException(); }

    /** build an element T from Element */
    protected ElementConverter<T> getConverter() { throw new IllegalStateException(); }

    /** @return the expression to use to extract the elements from the html */
    protected  String getSelectExpression() {  throw new IllegalStateException(); }
}
