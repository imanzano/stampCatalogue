package com.imanzano.sc.common.parser.html;

import org.jsoup.nodes.Element;

/**
 * Function convertion from Element to T
 */
@FunctionalInterface
public interface ElementConverter<T> {
    /**
     */
    T convert(Element e);
}
