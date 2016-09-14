package com.imanzano.sc.common.parser;

import org.jsoup.nodes.Element;

import java.io.IOException;
/**
 * Worker interface
 */
public interface Parser<R> {
    /** Process it*/
    R parse() throws IOException;

    /** Source */
    Parser<R> source(String s);

    String getSource();

    /** Source */
    Parser<R> fromString(String s);

    /** Parse from Element */
    Parser<R> from(Element element);
}
