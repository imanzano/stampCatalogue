package com.imanzano.sc.common.parser.html;

import java.io.IOException;

import com.imanzano.sc.common.parser.Tuple;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
/**
 * JsoupParser
 */
abstract class ElementParser<T> {

    private String name = null;
    private String expression = null;

    String getExpression()
    {
        return expression;
    }

    protected T evaluate(Document doc) throws IOException { throw new UnsupportedOperationException();}
    protected T evaluate(Element element) throws IOException{ throw new UnsupportedOperationException();}

    /** @param e Css Expression to use */
    ElementParser<T> using(String e)
    {
        expression = e;
        return this;
    }

    Tuple<String,T> parse(Document doc) throws IOException {
        return Tuple.tuple(name,evaluate(doc));
    }

    /** Set a name for the parsed elements.*/
    public ElementParser<T> as(String n)
    {
        name = n;
        return this;
    }
}
